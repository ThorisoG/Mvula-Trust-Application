package com.example.mvulatrustmobileapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView

class Program5 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_program5)

        val home_btn = findViewById<ImageView>(R.id.button4)
        home_btn.setOnClickListener {
            val fragment = User_home()
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit()
        }

        val donate_btn = findViewById<Button>(R.id.donate_btn)
        donate_btn.setOnClickListener {
            val fragment1 = Donation()
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment1)
                .commit()
        }

        val volunteer_btn = findViewById<Button>(R.id.volunteer_btn)
        volunteer_btn.setOnClickListener {
            val fragment2 = Volunteer_form()
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment2)
                .commit()
        }
    }
}
