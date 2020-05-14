package com.example.dietkuyy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView

class LoginActivity : AppCompatActivity() {
    private var mViewUser: EditText? = null
    private var mViewPassword: EditText? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        mViewUser = findViewById(R.id.et_emailSignin)
        mViewPassword = findViewById(R.id.et_passwordSignin)
        mViewPassword?.run {
            setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
                if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_NULL) {
                    razia()
                    return@OnEditorActionListener true
                }
                false
            })
        }
        findViewById<View>(R.id.button_signinSignin).setOnClickListener { razia() }
        findViewById<View>(R.id.button_signupSignin).setOnClickListener { startActivity(Intent(baseContext, RegisterActivity::class.java)) }
    }

    override fun onStart() {
        super.onStart()
        if (Preferences.getLoggedInStatus(baseContext)) {
            startActivity(Intent(baseContext, MainActivity::class.java))
            finish()
        }
    }

    private fun razia() {
        mViewUser!!.error = null
        mViewPassword!!.error = null
        var fokus: View? = null
        var cancel = false
        val user = mViewUser!!.text.toString()
        val password = mViewPassword!!.text.toString()
        if (TextUtils.isEmpty(user)) {
            mViewUser!!.error = "This field is required"
            fokus = mViewUser
            cancel = true
        } else if (!cekUser(user)) {
            mViewUser!!.error = "This Username is not found"
            fokus = mViewUser
            cancel = true
        }
        if (TextUtils.isEmpty(password)) {
            mViewPassword!!.error = "This field is required"
            fokus = mViewPassword
            cancel = true
        } else if (!cekPassword(password)) {
            mViewPassword!!.error = "This password is incorrect"
            fokus = mViewPassword
            cancel = true
        }
        if (cancel) fokus!!.requestFocus() else masuk()
    }

    private fun masuk() {
        Preferences.setLoggedInUser(baseContext, Preferences.getRegisteredUser(baseContext))
        Preferences.setLoggedInStatus(baseContext, true)
        startActivity(Intent(baseContext, MainActivity::class.java))
        finish()
    }

    private fun cekPassword(password: String): Boolean {
        return password == Preferences.getRegisteredPass(baseContext)
    }

    private fun cekUser(user: String): Boolean {
        return user == Preferences.getRegisteredUser(baseContext)
    }
}
