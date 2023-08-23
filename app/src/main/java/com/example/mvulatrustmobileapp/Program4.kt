package com.example.mvulatrustmobileapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class Program4 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_program4)

        var donate_btn = findViewById<ImageButton>(R.id.button)
        donate_btn.setOnClickListener {
            var Intent = Intent(this, Donation::class.java)
            startActivity(Intent)
        }

        var volunteer_btn = findViewById<ImageButton>(R.id.button1)
        volunteer_btn.setOnClickListener {
            var Intent = Intent(this, Volunteer_form::class.java)
            startActivity(Intent)
        }
    }
}