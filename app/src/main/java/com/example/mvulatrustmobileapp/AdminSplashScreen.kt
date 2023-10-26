package com.example.mvulatrustmobileapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class AdminSplashScreen : AppCompatActivity() {
    //Code has yet to be added here//
    private val AdminSplashtimeout: Long = 5000 // 5 seconds
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_splash_screen)

        // Handler to navigate to the next activity after the splashTimeOut
        Handler().postDelayed({
            //the Adminlogin is where is the intent where you want to go//
            val intent = Intent(this,
                AdminLogin::class.java)
            startActivity(intent)
            finish()
        }, AdminSplashtimeout)
    }
}