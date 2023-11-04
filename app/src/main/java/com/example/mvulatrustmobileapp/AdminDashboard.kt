package com.example.mvulatrustmobileapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class AdminDashboard : AppCompatActivity() {
    // Define variables for UI elements
    private lateinit var viewAllUsersButton: Button
    private lateinit var usersTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_dashboard)

        // Retrieve the email from SharedPreferences
        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val email = sharedPreferences.getString("email", null)

        // Check if email is null or empty
        if (email.isNullOrEmpty()) {
            // Redirect to login page
            showToast("Email is null or empty, redirecting to login page")
            redirectToLoginPage()
            return // Return to prevent further execution
        }
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
                //val password = usersCursor.getString(usersCursor.getColumnIndex("password"))
                usersStringBuilder.append("Email: $email\n")
            }
            usersTextView.text = usersStringBuilder.toString()
        }
    }
    private fun redirectToLoginPage() {
        val intent = Intent(this, AdminLogin::class.java)
        startActivity(intent)
        showToast("Logged out, redirecting to login page")
    }
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}