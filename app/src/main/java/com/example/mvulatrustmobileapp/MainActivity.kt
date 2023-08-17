package com.example.mvulatrustmobileapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var home = findViewById<TextView>(R.id.text_link)
        home.setOnClickListener {
            var Intent = Intent(this, MainActivity2::class.java)
            startActivity(Intent)
        }
    }
}