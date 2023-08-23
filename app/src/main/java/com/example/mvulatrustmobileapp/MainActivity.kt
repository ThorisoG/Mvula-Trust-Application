package com.example.mvulatrustmobileapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.os.Handler

class MainActivity : AppCompatActivity() {

    private val splashTimeOut: Long = 5000 // 5 seconds
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Handler to navigate to the next activity after the splashTimeOut
        Handler().postDelayed({
            //the Main activity 2 is the intent where you want to go//
            val intent = Intent(this,
                LoginPage::class.java)
            startActivity(intent)
            finish()
        }, splashTimeOut)
    }
}