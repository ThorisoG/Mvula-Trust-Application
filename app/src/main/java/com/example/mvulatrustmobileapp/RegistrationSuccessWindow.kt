package com.example.mvulatrustmobileapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class RegistrationSuccessWindow : AppCompatActivity() {
    //Code has yet to be implemented on this //
    private val Registrationtimeoout: Long = 5000 // 5 seconds
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration_success_window)

        // Handler to navigate to the next activity after the splashTimeOut
        Handler().postDelayed({
            //the Login page is the intent where you want to go//
            val intent = Intent(this,
                LoginPage::class.java)
            startActivity(intent)
            finish()
        }, Registrationtimeoout)
    }
}