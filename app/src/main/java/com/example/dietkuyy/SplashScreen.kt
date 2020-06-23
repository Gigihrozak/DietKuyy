package com.example.dietkuyy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import com.example.dietkuyy.screen.OnboardingExample1Activity

class SplashScreen : AppCompatActivity() {
private var bawah : Animation? = null
    private var atas : Animation? = null
    var ho :ImageView? = null
    var hi : TextView? = null
    var textView2 : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.requestFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_splash_screen)
        bawah = AnimationUtils.loadAnimation(this, R.anim.bawah)
        atas = AnimationUtils.loadAnimation(this,R.anim.atas)

        ho = findViewById(R.id.ho)
        hi = findViewById(R.id.hi)
        textView2 = findViewById(R.id.textView2)

        ho?.setAnimation(atas)
        hi?.setAnimation(bawah)
        textView2?.setAnimation(bawah)
        Handler().postDelayed({
            //start main activity
            startActivity(Intent(this@SplashScreen, OnboardingExample1Activity::class.java))
            //finish this activity
            finish()
        },5000)
    }
}
