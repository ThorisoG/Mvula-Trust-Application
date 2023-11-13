package com.example.mvulatrustmobileapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class IncomingVolunteers : AppCompatActivity(),AdapterView.OnItemClickListener {

    private lateinit var databaseHelper: DatabaseHelper
    private var selectedVolunteer: String = ""
    lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_incoming_volunteers)

        // Retrieve the email from SharedPreferences
        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val email = sharedPreferences.getString("email", null)

        // Check if email is null or empty
        if (email.isNullOrEmpty()) {
            // Redirect to login page
            showToast("Login")
            redirectToLoginPage()
            return // Return to prevent further execution
        }
        databaseHelper = DatabaseHelper(this)
        listView = findViewById(R.id.volunteersListView)

        val listArray = setByListView()
        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_single_choice, listArray)

        with(listView) {
            choiceMode = ListView.CHOICE_MODE_SINGLE
            adapter = arrayAdapter
            onItemClickListener = this@IncomingVolunteers // Assuming you implement AdapterView.OnItemClickListener in your class
        }
    }
    private fun setByListView(): ArrayList<String> {
        val arrayList: ArrayList<String> = ArrayList()
        val cursor = databaseHelper.getPendingVolunteers()

        while (cursor.moveToNext()) {
            val name = cursor.getString(cursor.getColumnIndex("Vname"))
            //val id = cursor.getString(cursor.getColumnIndex("Vidnum"))
            //val phone = cursor.getString(cursor.getColumnIndex("Phonenum"))
            //val email = cursor.getString(cursor.getColumnIndex("Email"))
            //val address = cursor.getString(cursor.getColumnIndex("HomeAddress"))
            val program = cursor.getString(cursor.getColumnIndex("Program"))
            //val qualification = cursor.getString(cursor.getColumnIndex("Qualification"))

            val volunteerInfo = """
            Name: $name
            Program: $program
        """.trimIndent()
            arrayList.add(volunteerInfo)
        }

        cursor.close()
        return arrayList
    }

    private fun getSelectedVname(selectedVolunteer: String): String {
        val cursor = databaseHelper.getPendingVolunteers()
        while (cursor.moveToNext()) {
            val name = cursor.getString(cursor.getColumnIndex("Vname"))
            if (selectedVolunteer.contains(name)) {
                return name
            }
        }
        return ""
    }
    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        selectedVolunteer = parent?.getItemAtPosition(position).toString()

        // Initialize selected details
        var selectedVname = ""
        var selectedId = ""
        var selectedPhone = ""
        var selectedEmail = ""
        var selectedAddress = ""
        var selectedProgram = ""
        var selectedQualification = ""

        val cursor = databaseHelper.getPendingVolunteers()

        while (cursor.moveToNext()) {
            val name = cursor.getString(cursor.getColumnIndex("Vname"))
            if (selectedVolunteer.contains(name)) {
                selectedVname = name
                selectedId = cursor.getString(cursor.getColumnIndex("Vidnum"))
                selectedPhone = cursor.getString(cursor.getColumnIndex("Phonenum"))
                selectedEmail = cursor.getString(cursor.getColumnIndex("Email"))
                selectedAddress = cursor.getString(cursor.getColumnIndex("HomeAddress"))
                selectedProgram = cursor.getString(cursor.getColumnIndex("Program"))
                selectedQualification = cursor.getString(cursor.getColumnIndex("Qualification"))
                break
            }
        }

        // Display Vname in a Toast message
        Toast.makeText(this, "Volunteer Selected: $selectedVname", Toast.LENGTH_SHORT).show()

        // Show the approval dialog with the details
        showApprovalDialog(selectedVname, selectedId, selectedPhone, selectedEmail, selectedAddress, selectedProgram, selectedQualification)
    }
    fun showApprovalDialog(selectedVname: String, selectedId: String, selectedPhone: String, selectedEmail: String, selectedAddress: String, selectedProgram: String, selectedQualification: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Approve Volunteer")
        builder.setMessage("Do you want to approve or reject this volunteer?\n\n" +
                "Name: $selectedVname\n" +
                "ID Number: $selectedId\n" +
                "Phone Number: $selectedPhone\n" +
                "Email: $selectedEmail\n" +
                "Address: $selectedAddress\n" +
                "Program: $selectedProgram\n" +
                "Qualification: $selectedQualification")

        builder.setPositiveButton("Approve") { _, _ ->
            // Update volunteer status to Approved
            val isSuccess = databaseHelper.updateVolunteerStatus(selectedVname, "Approved")

            if (isSuccess) {
                Toast.makeText(this, "Volunteer Approved", Toast.LENGTH_SHORT).show()
                // Refresh the list view or update it with the new status
                val listArray = setByListView()
                val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_single_choice, listArray)
                listView.adapter = arrayAdapter
            } else {
                Toast.makeText(this, "Error Approving Volunteer", Toast.LENGTH_SHORT).show()
            }
        }

        builder.setNegativeButton("Reject") { _, _ ->
            // Update volunteer status to Rejected
            val isSuccess = databaseHelper.updateVolunteerStatus(selectedVname, "Rejected")

            if (isSuccess) {
                Toast.makeText(this, "Volunteer Rejected", Toast.LENGTH_SHORT).show()
                // Refresh the list view or update it with the new status
                val listArray = setByListView()
                val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_single_choice, listArray)
                listView.adapter = arrayAdapter
            } else {
                Toast.makeText(this, "Error Rejecting Volunteer", Toast.LENGTH_SHORT).show()
            }
        }

        builder.setNeutralButton("No") { _, _ ->
            // Do nothing
        }
        val dialog = builder.create()
        dialog.show()
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