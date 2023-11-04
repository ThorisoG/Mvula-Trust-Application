package com.example.mvulatrustmobileapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class UserAccountSection : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_account_section)

        // Retrieve the email from SharedPreferences
        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val email = sharedPreferences.getString("email", null)

        // Check if email is null or empty
        if (email.isNullOrEmpty()) {
            // Redirect to login page
            showToast("Redirecting to login page")
            redirectToLoginPage()
            return // Return to prevent further execution
        }


        val deleteuser = findViewById<ImageView>(R.id.imageView12)
        deleteuser.setOnClickListener {
            // Code to run when imageView1 is clicked
            val intent = Intent(this, UserDelete::class.java)
            startActivity(intent)
        }

        val manageAdmin = findViewById<ImageView>(R.id.imageView16)
        manageAdmin.setOnClickListener{
            val intent = Intent(this, AccountManagement::class.java)
            startActivity(intent)
        }

        val managepassword = findViewById<ImageView>(R.id.imageView20)
        managepassword.setOnClickListener{
            val intent = Intent(this, UpdateAdminPassword::class.java)
            startActivity(intent)
        }


        //This is for when the user wants to Logout//
        val Logout = findViewById<ImageView>(R.id.imageView18)
        Logout.setOnClickListener {
            // Code to run when imageView1 is clicked
            showLogoutConfirmationDialog()
        }
    }
    private fun showLogoutConfirmationDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Logout")
            .setMessage("Are you sure you want to logout?")
            .setPositiveButton("Yes") { _, _ ->
                // Code to run when user clicks "Yes"
                // For example, you can perform logout here
                // Example: performLogout()
                performLogout()
            }
            .setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
    private fun performLogout() {
        // Clear the email from SharedPreferences
        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.remove("email")
        editor.apply()

        showToast("Logged out, redirecting to login page")

        // Redirect to login page and finish current activity
        val intent = Intent(this, LoginPage::class.java)
        startActivity(intent)
    }
    private fun redirectToLoginPage() {
        val intent = Intent(this, LoginPage::class.java)
        startActivity(intent)
        showToast("Logged out, redirecting to login page")
    }
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}