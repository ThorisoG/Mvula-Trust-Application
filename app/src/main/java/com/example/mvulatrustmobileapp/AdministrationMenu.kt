package com.example.mvulatrustmobileapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog

class AdministrationMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_administration_menu)

        showBetaPhaseDialog()

        val registrations = findViewById<ImageView>(R.id.registrations)
        registrations.setOnClickListener {
            // Code to run when imageView1 is clicked
            val intent = Intent(this, AdminDashboard::class.java)
            startActivity(intent)
        }

        val Volunteers = findViewById<ImageView>(R.id.volunteers)
        Volunteers.setOnClickListener {
            // Code to run when imageView1 is clicked
            val intent = Intent(this, IncomingVolunteers::class.java)
            startActivity(intent)
        }

        val donations = findViewById<ImageView>(R.id.Donations)
        donations.setOnClickListener {
            // Code to run when imageView1 is clicked
            val intent = Intent(this, IncomingDonations::class.java)
            startActivity(intent)
        }

        val accountuser = findViewById<ImageView>(R.id.UserAccounts)
        accountuser.setOnClickListener {
            val intent = Intent(this, UserAccountSection::class.java)
            startActivity(intent)
        }

        //This is for when the user wants to Logout//
        val Logout = findViewById<ImageView>(R.id.imageView13)
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
    private fun showBetaPhaseDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Caution : Beta Phase")
            .setMessage("Features are in the beta phase and yet to be fully tested.")
            .setPositiveButton("OK") { dialog, _ ->
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