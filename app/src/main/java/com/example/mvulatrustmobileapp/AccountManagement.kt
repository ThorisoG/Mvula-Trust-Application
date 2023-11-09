package com.example.mvulatrustmobileapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.ViewPropertyAnimatorListener

class AccountManagement : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account_management)

        // Retrieve the email from SharedPreferences
        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val email2 = sharedPreferences.getString("email", null)

        val dbHelper = DatabaseHelper(this)
        val adminsList = dbHelper.getAdmins()

        val listView = findViewById<ListView>(R.id.Admins)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, adminsList.map { it.joinToString(", ") })
        listView.adapter = adapter
        
        listView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            val selectedAdmin = adminsList[position]
            val email = selectedAdmin[0]

            if(email2 != email)
            {
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Deactivate Admin")
                builder.setMessage("Are you sure you want to deactivate this admin?")
                builder.setPositiveButton("Yes") { _, _ ->
                    val success = dbHelper.deactivateAdmin(email)
                    if (success) {
                        Toast.makeText(this, "Admin deactivated successfully", Toast.LENGTH_SHORT).show()

                        // Restart the activity to see the changes
                        finish()
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, "Failed to deactivate admin", Toast.LENGTH_SHORT).show()
                    }
                }
                builder.setNegativeButton("No") { _, _ -> }
                builder.show()
            }
            else
            {
                showToast("You cannot deactivate your Account while logged in")
            }
        }

        //This its to display the deactivated Administrator//
        val listView2 = findViewById<ListView>(R.id.Admins2)
        val deactivatedAdminsList = dbHelper.getDeactivatedAdmins()

        val adapter2 = ArrayAdapter(this, android.R.layout.simple_list_item_1, deactivatedAdminsList.map { it.joinToString(", ") })
        listView2.adapter = adapter2

        listView2.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            val selectedAdmin = deactivatedAdminsList[position]
            val email = selectedAdmin[0]

            val builder = AlertDialog.Builder(this)
            builder.setTitle("Activate Admin")
            builder.setMessage("Are you sure you want to activate this admin?")
            builder.setPositiveButton("Yes") { _, _ ->
                val success = dbHelper.activateAdmin(email)

                if (success) {
                    Toast.makeText(this, "Admin activated successfully", Toast.LENGTH_SHORT).show()

                    // Restart the activity to see the changes
                    finish()
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Failed to activate admin", Toast.LENGTH_SHORT).show()
                }
            }
            builder.setNegativeButton("No") { _, _ -> }

            builder.show()
        }

    }
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}