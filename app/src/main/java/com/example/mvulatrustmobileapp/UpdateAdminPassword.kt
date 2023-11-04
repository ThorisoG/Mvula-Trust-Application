package com.example.mvulatrustmobileapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class UpdateAdminPassword : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_admin_password)

        // Retrieve the email from SharedPreferences
        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val email = sharedPreferences.getString("email", null)

        // Check if email is null or empty
        if (email.isNullOrEmpty()) {
            // Redirect to login page
            showToast("Email is null or empty, redirecting to login page")
            return // Return to prevent further execution
        }
        val getEmailTextView = findViewById<TextView>(R.id.getemail)
        // Set the text of the TextView to the email
        getEmailTextView.text = email
    }
    fun UpdatePass(view: View)
    {
        // Retrieve the new password from the EditText
        val newPassword = findViewById<EditText>(R.id.editTextTextPassword2).text.toString()

        // Show a confirmation dialog
        AlertDialog.Builder(this)
            .setTitle("Confirmation")
            .setMessage("Are you sure you want to change your password?")
            .setPositiveButton(android.R.string.yes) { _, _ ->
                // User clicked "Yes", proceed with password change
                proceedWithPasswordChange(newPassword)
            }
            .setNegativeButton(android.R.string.no, null)
            .setIcon(android.R.drawable.ic_dialog_alert)
            .show()
    }
    private fun proceedWithPasswordChange(newPassword: String) {
        // Retrieve the email from SharedPreferences
        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val email = sharedPreferences.getString("email", null)

        // Check if email or newPassword is null or empty
        if (email.isNullOrEmpty() || newPassword.isNullOrEmpty()) {
            showToast("Email or Password is null or empty")
            return // Return to prevent further execution
        }

        // Use your DatabaseHelper to update the admin's password
        val databaseHelper = DatabaseHelper(this)
        val result = databaseHelper.updateAdminPassword(email, newPassword)

        if (result) {
            showToast("Password updated successfully")
        } else {
            showToast("Failed to update password")
        }
    }
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}