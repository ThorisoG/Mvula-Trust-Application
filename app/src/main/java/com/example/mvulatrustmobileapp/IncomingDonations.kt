package com.example.mvulatrustmobileapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView

class IncomingDonations : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_incoming_donations)

        val db = DatabaseHelper(this)

        // Assuming R.id.DonationSum is the correct ID for your TextView
        val donationSumTextView = findViewById<TextView>(R.id.DonationSum)
        val totalAmount = db.getTotalDonationAmount()
        donationSumTextView.text = "R$totalAmount"

        // Find the ListView
        val listView = findViewById<ListView>(R.id.DonatorsList)

        // Get the list of donations from the database
        val donationsList = db.getAllDonations()

        // Create an adapter to bind the data to the ListView
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, donationsList)

        // Set the adapter for the ListView
        listView.adapter = adapter

        db.close()
    }

    fun Back(view: View)
    {
        val intent = Intent(this, IncomingVolunteers::class.java)
        startActivity(intent)
    }
}