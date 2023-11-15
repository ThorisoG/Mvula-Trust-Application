package com.example.mvulatrustmobileapp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.ImageButton
import android.widget.Toast
import android.app.Activity
import android.app.AlertDialog
import android.net.Uri
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.google.android.material.floatingactionbutton.FloatingActionButton


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Volunteer_form.newInstance] factory method to
 * create an instance of this fragment.
 */
class Volunteer_form : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private val PICK_FILE_REQUEST = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val uploadbutton = view.findViewById<ImageButton>(R.id.uploadButton)
        uploadbutton.setOnClickListener {
            val intent = Intent(requireContext(), UploadDocuments::class.java)
            startActivity(intent)
        }

        //Enabling the User to Donate to the Administrator//
        val submitApplicationButton = view.findViewById<Button>(R.id.imageButton)

        submitApplicationButton.setOnClickListener {
            val name = view.findViewById<EditText>(R.id.userName).text.toString()
            val idNum = view.findViewById<EditText>(R.id.idnum).text.toString()
            val phoneNum = view.findViewById<EditText>(R.id.cellno).text.toString()
            val email = view.findViewById<EditText>(R.id.email).text.toString()
            val address = view.findViewById<EditText>(R.id.address).text.toString()
            val program = view.findViewById<AutoCompleteTextView>(R.id.auto_complete1).text.toString()
            val qualification = view.findViewById<AutoCompleteTextView>(R.id.auto_complete2).text.toString()
            val volunteerStatus = "Pending" // all new volunteers start at "Pending"

            // Perform validation //
            if (name.isEmpty() || idNum.isEmpty() || phoneNum.isEmpty() || email.isEmpty() || address.isEmpty() || program.isEmpty() || qualification.isEmpty()) {
                // Show an error message if any field is empty
                Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (idNum.length != 13 || !idNum.all { it.isDigit() }) {
                // Show an error message if the ID number is not exactly 13 digits or contains non-digit characters
                Toast.makeText(requireContext(), "Invalid ID number. It should be 13 digits.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            //Validating the email to ensure accurate input//
            val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
            if (!email.matches(emailPattern.toRegex())) {
                // Show an error message if the email format is invalid
                Toast.makeText(requireContext(), "Invalid email address.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                // Show an error message if the email format is invalid
                Toast.makeText(requireContext(), "Invalid email address.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            //validating phone number//
            if (phoneNum.length != 10 || !phoneNum.all { it.isDigit() }) {
                // Show an error message if the phone number is not exactly 10 digits or contains non-digit characters
                Toast.makeText(requireContext(), "Invalid phone number. It should be 10 digits.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            //Validating Home Address//
            if (address.isEmpty()) {
                // Show an error message if the address is empty
                Toast.makeText(requireContext(), "Please enter your home address", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            val dbHelper = DatabaseHelper(requireContext())

            AlertDialog.Builder(requireContext())
                .setTitle("Confirm Submission")
                .setMessage("Do you want to submit this volunteer application?")
                .setPositiveButton(android.R.string.yes) { _, _ ->
                    // User clicked "Yes", proceed with application submission
                    val success = dbHelper.insertVolunteerData(name, idNum, phoneNum, email, address, program, qualification, volunteerStatus)

                    if (success) {
                        Toast.makeText(requireContext(), "Volunteer data inserted successfully", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(requireContext(), "Failed to insert volunteer data", Toast.LENGTH_SHORT).show()
                    }
                }
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show()
        }

        val items = listOf(
            "Water Resource Management",
            "Training and Advocacy",
            "Sustainable Water and Sanitation",
            "Local Government Support",
            "Infrastructure Creation and Property Development"
        )


        val autoComplete1: AutoCompleteTextView =
            view.findViewById(R.id.auto_complete1) // Use a different ID
        val adapter1 = ArrayAdapter(requireContext(), R.layout.list_item, items)
        autoComplete1.setAdapter(adapter1)

        autoComplete1.setOnItemClickListener { _, _, i, _ ->
            val itemSelected = adapter1.getItem(i)
            Toast.makeText(requireContext(), "Item: $itemSelected", Toast.LENGTH_SHORT).show()
        }

        val itemList = listOf(
            "Grade 10",
            "Grade 12 (Matric)",
            "Higher Certificate",
            "Diploma",
            "Degree",
            "Other"
        )

        val autoComplete2: AutoCompleteTextView =
            view.findViewById(R.id.auto_complete2) // Use a different ID
        val adapter2 = ArrayAdapter(requireContext(), R.layout.list_items, itemList)
        autoComplete2.setAdapter(adapter2)

        autoComplete2.setOnItemClickListener { _, _, i, _ ->
            val itemSelected = adapter2.getItem(i)
            Toast.makeText(requireContext(), "Item: $itemSelected", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_volunteer_form, container, false)
        val view = inflater.inflate(R.layout.fragment_volunteer_form, container, false)

        val uploadButton = view.findViewById<ImageButton>(R.id.uploadButton)
        uploadButton.setOnClickListener {
            openFileChooser()
        }

        val uploadButton1 = view.findViewById<ImageButton>(R.id.uploadButton1)
        uploadButton1.setOnClickListener {
            openFileChooser()
        }

        //the floating chat button
        val chatButton = view.findViewById<FloatingActionButton>(R.id.chatbutton)
        chatButton.setOnClickListener {
            val intent1 = Intent(requireContext(), MainActivity3::class.java)
            requireContext().startActivity(intent1)
        }

        return view
    }

    private fun openFileChooser() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "application/pdf|application/msword|application/vnd.openxmlformats-officedocument.wordprocessingml.document"
        startActivityForResult(intent, PICK_FILE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_FILE_REQUEST && resultCode == Activity.RESULT_OK) {
            if (data != null && data.data != null) {
                val selectedFileUri: Uri = data.data!!
                // Now you can handle the selected file URI (upload it, etc.)
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Volunteer_form.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Volunteer_form().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


    }
