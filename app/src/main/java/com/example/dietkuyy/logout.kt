package com.example.dietkuyy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class logout : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logout)
        val nama = findViewById<TextView>(R.id.tv_namaMain)
        nama.text = Preferences.getLoggedInUser(baseContext)
        findViewById<View>(R.id.button_logout).setOnClickListener {
            Preferences.clearLoggedInUser(baseContext)
            startActivity(Intent(baseContext, LoginActivity::class.java))
            finish()
        }
    }
}
