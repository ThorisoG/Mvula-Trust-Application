package com.example.mvulatrustmobileapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast

class AdminLogin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_login)
    }
    fun Login(view: View)
    {
        val emailEditText = findViewById<EditText>(R.id.editTextTextEmailAddress)
        val passwordEditText = findViewById<EditText>(R.id.editTextTextPassword)

        val email = emailEditText.text.toString()
        val password = passwordEditText.text.toString()

        val dbHelper = DatabaseHelper(this)

        if (dbHelper.checkAdminLogin(email, password)) {
            // If login is successful, start the next activity

            //Making this accessible to the new page//
            val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString("email", email)
            editor.apply()

            val intent = Intent(this, AdministrationMenu::class.java)
            startActivity(intent)
            // Finish the current activity to prevent going back to login screen

            // Show a toast for successful login
            Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
        } else {
            // Check if the admin account is deactivated
            val isAdminDeactivated = dbHelper.getAdminDeactivationStatus(email)
            if (isAdminDeactivated) {
                // Show a toast for deactivated admin account
                Toast.makeText(this, "Admin Account currently deactivated", Toast.LENGTH_SHORT).show()
            } else {
                // Show a toast for invalid credentials
                Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show()
            }
        }
    }
    override fun onBackPressed() {
        // Preventing the user From Accessing other pages or potentially breaking the app//
        Toast.makeText(this, "Hey, you can't go back at this stage", Toast.LENGTH_SHORT).show()
    }

    fun Prev(view: View)
    {
        val intent = Intent(this, LoginPage::class.java)
        startActivity(intent)
    }
}