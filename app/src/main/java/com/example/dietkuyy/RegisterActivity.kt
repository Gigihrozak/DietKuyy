package com.example.dietkuyy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView
import com.example.dietkuyy.Preferences.getRegisteredUser
import com.example.dietkuyy.Preferences.setRegisteredPass
import com.example.dietkuyy.Preferences.setRegisteredUser

class RegisterActivity : AppCompatActivity() {
    private var mViewUser: EditText? = null
    private var mViewPassword: EditText? = null
    private var mViewRepassword: EditText? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        mViewUser = findViewById(R.id.et_emailSignup)
        mViewPassword = findViewById(R.id.et_passwordSignup)
        mViewRepassword = findViewById(R.id.et_passwordSignup2)
        mViewRepassword?.run {
            setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
                if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_NULL) {
                    razia()
                    return@OnEditorActionListener true
                }
                false
            })
        }
        findViewById<View>(R.id.button_signupSignup).setOnClickListener { razia() }
    }

    private fun razia() {
        mViewUser!!.error = null
        mViewPassword!!.error = null
        mViewRepassword!!.error = null
        var fokus: View? = null
        var cancel = false
        val repassword = mViewRepassword!!.text.toString()
        val user = mViewUser!!.text.toString()
        val password = mViewPassword!!.text.toString()
        if (TextUtils.isEmpty(user)) {
            mViewUser!!.error = "This field is required"
            fokus = mViewUser
            cancel = true
        } else if (cekUser(user)) {
            mViewUser!!.error = "This Username is already exist"
            fokus = mViewUser
            cancel = true
        }
        if (TextUtils.isEmpty(password)) {
            mViewPassword!!.error = "This field is required"
            fokus = mViewPassword
            cancel = true
        } else if (!cekPassword(password, repassword)) {
            mViewRepassword!!.error = "This password is incorrect"
            fokus = mViewRepassword
            cancel = true
        }
        if (cancel) {
            fokus!!.requestFocus()
        } else {
            setRegisteredUser(baseContext, user)
            setRegisteredPass(baseContext, password)
            finish()
        }
    }

    private fun cekPassword(password: String, repassword: String): Boolean {
        return password == repassword
    }

    private fun cekUser(user: String): Boolean {
        return user == getRegisteredUser(baseContext)
    }
}
