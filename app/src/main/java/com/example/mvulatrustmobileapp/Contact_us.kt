package com.example.mvulatrustmobileapp

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Contact_us.newInstance] factory method to
 * create an instance of this fragment.
 */
class Contact_us : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
/*
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Enabling the User to Donate to the Administrator//
        val submitApplicationButton = view.findViewById<ImageButton>(R.id.submitbtn)

        submitApplicationButton.setOnClickListener {
            val myName = view.findViewById<EditText>(R.id.userName).text.toString()
            val mySurname = view.findViewById<EditText>(R.id.lastname).text.toString()
            val myPhoneNum = view.findViewById<EditText>(R.id.cellnum).text.toString()
            val myEmail = view.findViewById<EditText>(R.id.emailAddress).text.toString()
            val myMessage = view.findViewById<EditText>(R.id.myMessage).text.toString()

            val dbHelper = DatabaseHelper(requireContext())

            /*AlertDialog.Builder(requireContext())
                .setTitle("Confirm Submission")
                .setMessage("Do you want to submit this message ?")
                .setPositiveButton(android.R.string.yes) { _, _ ->
                    // User clicked "Yes", proceed with application submission
                    val success = dbHelper.insertVolunteerData(myName, mySurname, myPhoneNum, myEmail, myMessage, messageStatus)

                    if (success) {
                        Toast.makeText(requireContext(), "Message sent", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(requireContext(), "Failed to insert data", Toast.LENGTH_SHORT).show()
                    }
                }
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show()*/
        }

    }

*/
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact_us, container, false)

        val rootView = inflater.inflate(R.layout.fragment_contact_us, container, false)

        val chatButton = rootView.findViewById<FloatingActionButton>(R.id.chatbutton)
        chatButton.setOnClickListener {
            // Here, you can launch a new activity by creating an Intent for it
            val intent1 = Intent(requireContext(), MainActivity3::class.java)
            requireContext().startActivity(intent1)
        }

        return rootView

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Contact_us.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Contact_us().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}