package com.example.dietkuyy.screen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import com.example.dietkuyy.login.LoginActivity
import com.example.dietkuyy.R
import com.thecode.onboardingviewagerexamples.utils.Animatoo
import kotlinx.android.synthetic.main.activity_onboarding_finish.*

class OnboardingFinishActivity : AppCompatActivity() {
    private lateinit var btnStart: LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding_finish)
        btnStart = layout_start
        btnStart.setOnClickListener {
            finish()
            val intent =
                Intent(applicationContext, LoginActivity::class.java)
            startActivity(intent)
            Animatoo.animateSlideLeft(this)
        }
    }
}
