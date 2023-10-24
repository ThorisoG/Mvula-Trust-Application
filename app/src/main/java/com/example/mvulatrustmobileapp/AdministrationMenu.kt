package com.example.mvulatrustmobileapp

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.NotificationCompat

class AdministrationMenu : AppCompatActivity() {

    private val CHANNEL_ID = "new_activity_channel"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_administration_menu)

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
        else
        {
            showToast("Email is not null or empty, redirecting to login page")
        }

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

        // Initialize DatabaseHelper
        var databaseHelper = DatabaseHelper(this)

        // Check for new registrations, volunteers, and donations
        val newRegistrationsCount = databaseHelper.getNewRegistrationsCount()
        if (newRegistrationsCount > 0) {
            triggerNotification("$newRegistrationsCount new registrations", 1)
        }

        val newVolunteersCount = databaseHelper.getNewVolunteersCount()
        if (newVolunteersCount > 0) {
            triggerNotification("$newVolunteersCount new volunteer applications", 2)
        }

        val newDonationsCount = databaseHelper.getNewDonationsCount()
        if (newDonationsCount > 0) {
            triggerNotification("$newDonationsCount new donations", 3)
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

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "New Activity Channel"
            val descriptionText = "Channel for new activities"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun triggerNotification(message: String, notificationId: Int) {
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Create an Intent to navigate to the specified page
        val intent: Intent = when (notificationId) {
            1 -> Intent(this, AdminDashboard::class.java)
            2 -> Intent(this, IncomingVolunteers::class.java)
            3 -> Intent(this, IncomingDonations::class.java)
            else -> Intent(this, AdministrationMenu::class.java)
        }

        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        // Create a notification
        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.appicon)
            .setContentTitle("MvulaTrust Mobile®")
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent) // Attach the PendingIntent
            .setAutoCancel(true) // Automatically dismiss the notification

        // Show the notification with a unique ID
        notificationManager.notify(notificationId, builder.build())
    }

    private fun redirectToLoginPage() {
        val intent = Intent(this, LoginPage::class.java)
        startActivity(intent)
        showToast("Logged out, redirecting to login page")
        finish() // Finish the current activity
    }
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}