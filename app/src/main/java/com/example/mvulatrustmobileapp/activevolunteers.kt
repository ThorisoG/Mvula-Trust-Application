package com.example.mvulatrustmobileapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.SimpleCursorAdapter

class activevolunteers : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_activevolunteers)

        val list = findViewById<ListView>(R.id.listview)

        // Create an instance of DatabaseHelper
        val dbHelper = DatabaseHelper(this)

        // Get the list of approved volunteers
        val cursor = dbHelper.getApprovedVolunteers()

        // Define the columns from the cursor to use in the adapter
        val fromColumns = arrayOf("Vname", "Program")
        val toViews = intArrayOf(android.R.id.text1, android.R.id.text2)

        // Create the adapter and set it to the ListView
        val adapter = SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, cursor, fromColumns, toViews, 0)
        list.adapter = adapter

    }
}
