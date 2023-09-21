package com.example.mvulatrustmobileapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class IncomingVolunteers : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_incoming_volunteers)
    }

    fun Donations(view: View)
    {
        val intent = Intent(this, IncomingDonations::class.java)
        startActivity(intent)
    }

    fun Back(view: View)
    {
        val intent = Intent(this, AdminDashboard::class.java)
        startActivity(intent)
    }
}