package com.example.dietkuyy.login

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.dietkuyy.MainActivity
import com.example.dietkuyy.R
import com.firebase.ui.auth.AuthUI
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.thecode.aestheticdialogs.AestheticDialog

import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity()  {
    private var auth = FirebaseAuth.getInstance()
    private lateinit var googleSignInClient: GoogleSignInClient
    private val progressBar = CustomProgressBar()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        daftar_sign.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.web_client))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        btn_login.setOnClickListener {
            EmailSignIn()
        }

        google_login.setOnClickListener {
            signIn()
        }
    }

    private fun EmailSignIn() {
        val email = emailin.text.toString().trim()
        val pass = inputPassword.text.toString().trim()
        when {
            email.isEmpty() -> {
                emailin.error = "Tidak Boleh Kosong"
            }
            inputPassword.length() < 6 -> {
                inputPassword.error = "Password Harus Lebih Dari 6 Karakter"
            }
            else -> {
                progressBar.show(this, "Mencoba Masuk...")
                auth.signInWithEmailAndPassword(email, pass)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            AestheticDialog.showToaster(
                                this,
                                "Success",
                                "Berhasil Masuk",
                                AestheticDialog.SUCCESS
                            )
                            updateUI(auth.currentUser)
                        } else Log.w(TAG, "signInWithCredential:failure", it.exception)
                        progressBar.dialog!!.dismiss()
                    }
                    .addOnFailureListener {
                        Log.d("Main", "signInWithCredential:failure")
                        progressBar.dialog!!.dismiss()
                        AestheticDialog.showToaster(
                            this,
                            "Gagal Login",
                            "Email atau Password Salah",
                            AestheticDialog.ERROR
                        )
                    }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account!!)
            } catch (e: ApiException) {
                Log.w(TAG, "Google sign in failed", e)
            }
        }
    }

    private fun firebaseAuthWithGoogle(account: GoogleSignInAccount) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + account.id!!)

        progressBar.show(this, "Tunggu Sebentar...")
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                }
                progressBar.dialog!!.dismiss()
            }
    }

    private fun updateUI(user: FirebaseUser?) {
        SessionData.session(this)
        SessionData["UserData"] = user!!.uid
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
        finish()
    }

    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onStart() {
        super.onStart()
        if (auth.currentUser != null) {
            val currentUser = auth.currentUser
            updateUI(currentUser)
        }
    }

    companion object {
        const val TAG = "GoogleActivity"
        private const val RC_SIGN_IN = 9001
    }
    }


