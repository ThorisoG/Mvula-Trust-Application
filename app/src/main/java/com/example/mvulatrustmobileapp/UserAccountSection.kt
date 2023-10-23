package com.example.mvulatrustmobileapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog

class UserAccountSection : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_account_section)

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
        // Assuming LoginActivity is the name of your login activity
        val intent = Intent(this, LoginPage::class.java)
        startActivity(intent)

        // Finish the current activity to prevent the user from navigating back to it
        finish()
    }
}