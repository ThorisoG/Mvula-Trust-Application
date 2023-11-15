package com.example.mvulatrustmobileapp

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.Image
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
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
            showToast("welcome $email")
        }
        val welcomemessage = findViewById<TextView>(R.id.textView20)
        welcomemessage.text = "Welcome Administrator $email"

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

        //Added New Features on the menu//
        val accountuser = findViewById<ImageView>(R.id.imageView25)
        accountuser.setOnClickListener {
            val intent = Intent(this, UserDelete::class.java)
            startActivity(intent)
        }

        val updatepassword = findViewById<ImageView>(R.id.updatepass)
        updatepassword.setOnClickListener {
            val intent = Intent(this, UpdateAdminPassword::class.java)
            startActivity(intent)
        }

        val activateaccount  = findViewById<ImageView>(R.id.imageView24)
        activateaccount.setOnClickListener {
            val intent = Intent(this, AccountManagement::class.java)
            startActivity(intent)
        }

        //this is to show the version number//
        val version = findViewById<ImageView>(R.id.imageView32)
        version.setOnClickListener {
            val versionMessage = "Mvula trust Beta ver 0.3"
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Version Information")
                .setMessage(versionMessage)
                .setPositiveButton("OK") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }

        val newfeature = findViewById<ImageView>(R.id.imageView33)
        newfeature.setOnClickListener {
            val intent = Intent(this, activevolunteers::class.java)
            startActivity(intent)
        }

        val notificationBell = findViewById<ImageView>(R.id.Bell)
        notificationBell.setOnClickListener {
            val dbhelper = DatabaseHelper(this)
            val newVolunteersCount = dbhelper.getNewVolunteersCount()
            val newDonationsCount = dbhelper.getNewDonationsCount()
            val message = if (newVolunteersCount > 0 || newDonationsCount > 0) {
                "There are new volunteers received :$newVolunteersCount " +
                        "\nThere are new Donations received:$newDonationsCount"
            } else {
                "Everything's good for now"
            }
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Notification")
                .setMessage(message)
                .setPositiveButton("OK") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }
        //This is for when the user wants to Logout//
        val Logout = findViewById<ImageView>(R.id.imageView13)
        Logout.setOnClickListener {
            // Code to run when imageView1 is clicked
            showLogoutConfirmationDialog()
        }

        // Initialize DatabaseHelper
        var databaseHelper = DatabaseHelper(this)
        notificationBell.setImageResource(R.drawable.notificationactive)

        // Check for new registrations, volunteers, and donations
        val newVolunteersCount = databaseHelper.getNewVolunteersCount()
        if (newVolunteersCount > 0) {
            notificationBell.setImageResource(R.drawable.notificationactive)
            triggerNotification("$newVolunteersCount new volunteer applications", 2)
        }

        val newDonationsCount = databaseHelper.getNewDonationsCount()
        if (newDonationsCount > 0) {
            notificationBell.setImageResource(R.drawable.notificationactive)
            triggerNotification("$newDonationsCount new donations", 3)
        }
    }

    private fun showLogoutConfirmationDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Logout")
            .setMessage("Are you sure you want to logout?")
            .setPositiveButton("Yes") { _, _ ->
                performLogout()
            }
            .setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
    private fun showBetaPhaseDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Caution : Beta Phase 3")
            .setMessage("Features on this application are in phase 3 of the testing phase \n and yet to be fully tested")
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
    override fun onBackPressed() {
        // Preventing the user From Accessing other pages or potentially breaking the app//
        Toast.makeText(this, "Hey, you can't go back at this stage", Toast.LENGTH_SHORT).show()
    }
}