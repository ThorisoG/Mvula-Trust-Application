package com.example.mvulatrustmobileapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class activevolunteers : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_activevolunteers)

        val activeVol = findViewById<TextView>(R.id.volunteerlist)

        // Initialize your DatabaseHelper
        val dbHelper = DatabaseHelper(this)

        // Get the list of approved volunteers from the database
        val approvedVolunteers = dbHelper.getApprovedVolunteers()

        if (approvedVolunteers.isEmpty()) {
            // If no volunteers are found, set a specific message
            activeVol.text = "No active volunteers at the moment"
        } else {
            // Construct a string to display in the TextView
            val displayText = StringBuilder()
            for (volunteer in approvedVolunteers) {
                val name = volunteer[0]
                val program = volunteer[1]
                displayText.append("Name: $name,\n Program: $program\n")
            }

            // Set the text in the TextView
            activeVol.text = displayText.toString()
        }
    }
}
