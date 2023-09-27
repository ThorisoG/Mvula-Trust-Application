package com.example.mvulatrustmobileapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class AdminDashboard : AppCompatActivity() {
    // Define variables for UI elements
    private lateinit var viewAllUsersButton: Button
    private lateinit var usersTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_dashboard)

        // Connect UI elements to variables
        viewAllUsersButton = findViewById(R.id.viewAllUsersButton)
        usersTextView = findViewById(R.id.users)

        //This is the Admin Viewing List of the registered users//
        viewAllUsersButton.setOnClickListener {

            val databaseHelper = DatabaseHelper(this)
            val usersCursor = databaseHelper.getAllUsers()
            val usersStringBuilder = StringBuilder()
            while (usersCursor.moveToNext()) {
                val email = usersCursor.getString(usersCursor.getColumnIndex("email"))
                val password = usersCursor.getString(usersCursor.getColumnIndex("password"))
                usersStringBuilder.append("Email: $email, Password: $password\n")
            }
            usersTextView.text = usersStringBuilder.toString()
        }
    }

    //Deleting the selected user from the system/ database//
    fun Delete(view: View)
    {
        val intent = Intent(this, UserDelete::class.java)
        startActivity(intent)
    }

    //Going to the volunteers page//
    fun Volunteers(view: View)
    {
        val intent = Intent(this, IncomingVolunteers::class.java)
        startActivity(intent)
    }
}