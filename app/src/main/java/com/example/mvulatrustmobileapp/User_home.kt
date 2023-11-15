package com.example.mvulatrustmobileapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.GridView
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.google.android.material.floatingactionbutton.FloatingActionButton

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [User_home.newInstance] factory method to
 * create an instance of this fragment.
 */
class User_home : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var chatbot: ImageButton
    private var userEmail: String? = null

    private lateinit var galleryButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
            userEmail = it.getString("email")
        }

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.fragment_user_home, container, false)

        val emailTextView = rootView.findViewById<TextView>(R.id.greeting)
        emailTextView.text = "User Email: $userEmail"

        val program1 = rootView.findViewById<CardView>(R.id.program1)  //Water Resource Management
        val program2 = rootView.findViewById<CardView>(R.id.program2) //Training and Advocacy
        val program3 = rootView.findViewById<CardView>(R.id.program3) //Sustainable Water and Sanitation
        val program4 = rootView.findViewById<CardView>(R.id.program4) //Local Government Support
        val program5 = rootView.findViewById<CardView>(R.id.program5) //Infrastructure Creation and Property Development"
        chatbot = rootView.findViewById(R.id.chatbot)

        galleryButton = rootView.findViewById<ImageButton>(R.id.gallery)  // Initialize galleryButton
        galleryButton.setOnClickListener {
            val intent = Intent(requireContext(), GalleryPage::class.java)
            startActivity(intent)
        }

        program1.setOnClickListener {
            val intent = Intent(requireContext(), Program1::class.java)
            startActivity(intent)
        }

        program2.setOnClickListener {
            val intent = Intent(requireContext(), Program2::class.java)
            startActivity(intent)
        }

        program3.setOnClickListener {
            val intent = Intent(requireContext(), Program3::class.java)
            startActivity(intent)
        }

        program4.setOnClickListener {
            val intent = Intent(requireContext(), Program4::class.java)
            startActivity(intent)
        }

        program5.setOnClickListener {
            val intent = Intent(requireContext(), Program5::class.java)
            startActivity(intent)
        }
        chatbot.setOnClickListener {
            val intent = Intent(requireContext(), MainActivity3::class.java)
            startActivity(intent)
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
         * @return A new instance of fragment User_home.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            User_home().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}