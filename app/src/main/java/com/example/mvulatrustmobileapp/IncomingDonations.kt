package com.example.mvulatrustmobileapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class IncomingDonations : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_incoming_donations)
    }

    fun Back(view: View)
    {
        val intent = Intent(this, IncomingVolunteers::class.java)
        startActivity(intent)
    }
}