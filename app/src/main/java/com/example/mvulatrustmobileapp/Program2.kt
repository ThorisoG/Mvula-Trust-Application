package com.example.mvulatrustmobileapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class Program2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_program2)

        val home_btn = findViewById<ImageButton>(R.id.button4)
        home_btn.setOnClickListener {
            val fragment = User_home()
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container2, fragment)
                .commit()
        }

        val donate_btn = findViewById<ImageButton>(R.id.button)
        donate_btn.setOnClickListener {
            val fragment = Donation()
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container1, fragment)
                .commit()
        }

        val volunteer_btn = findViewById<ImageButton>(R.id.button1)
        volunteer_btn.setOnClickListener {
            val fragment = Volunteer_form()
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit()
        }


    }
}