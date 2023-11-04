package com.example.mvulatrustmobileapp

import android.app.Application
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class UserDelete : AppCompatActivity(), AdapterView.OnItemClickListener {

    lateinit var databaseHelper: DatabaseHelper
    lateinit var listView: ListView
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_delete)

        databaseHelper = DatabaseHelper(this)
        listView = findViewById(R.id.UserList)

        val listArray: ArrayList<String> = setByListView()
        val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_list_item_single_choice, listArray)
        listView.choiceMode = ListView.CHOICE_MODE_SINGLE
        listView.adapter = arrayAdapter
        listView.onItemClickListener = this

    }
    private fun setByListView(): ArrayList<String> {
        val arrayList: ArrayList<String> = ArrayList()
        val cursor = databaseHelper.getAllUsers()

        while (cursor.moveToNext()) {
            val email = cursor.getString(cursor.getColumnIndex("email"))
            arrayList.add(email)
        }

        cursor.close()
        return arrayList
    }
    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val email = parent?.getItemAtPosition(position).toString()
        Toast.makeText(applicationContext, "You selected $email", Toast.LENGTH_LONG).show()
    }

    private fun showDeleteConfirmationDialog(email: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Confirm Deletion")
            .setMessage("Are you sure you want to delete the user $email?")
            .setPositiveButton("Yes") { _, _ ->
                // User clicked "Yes", proceed with deletion
                deleteUser(email)
            }
            .setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
    fun DeleteUser(view: View) {
        val selectedPosition = listView.checkedItemPosition
        if (selectedPosition != AdapterView.INVALID_POSITION) {
            val email = listView.getItemAtPosition(selectedPosition).toString()

            showDeleteConfirmationDialog(email)
        } else {
            Toast.makeText(applicationContext, "No user selected", Toast.LENGTH_SHORT).show()
        }
    }
      private fun deleteUser(email: String) {
          val success = databaseHelper.deleteUser(email)

          if (success) {
              Toast.makeText(applicationContext, "User $email is deleted", Toast.LENGTH_SHORT).show()

              // Refresh the list after deletion if needed
              val listArray: ArrayList<String> = setByListView()
              val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_list_item_single_choice, listArray)
              listView.adapter = arrayAdapter
          } else {
              Toast.makeText(applicationContext, "Error deleting user", Toast.LENGTH_SHORT).show()
          }
      }
}