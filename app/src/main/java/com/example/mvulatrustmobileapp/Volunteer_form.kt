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
import android.net.Uri


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
