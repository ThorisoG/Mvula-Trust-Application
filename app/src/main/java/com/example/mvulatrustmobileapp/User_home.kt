package com.example.mvulatrustmobileapp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_home, container, false)

                val rootView = inflater.inflate(R.layout.fragment_user_home, container, false)

                val button1 = rootView.findViewById<ImageButton>(R.id.program1)
                val button2 = rootView.findViewById<ImageButton>(R.id.program2)
                val button3 = rootView.findViewById<ImageButton>(R.id.program3)
                val button4 = rootView.findViewById<ImageButton>(R.id.program4)
                val button5 = rootView.findViewById<ImageButton>(R.id.program5)

                button1.setOnClickListener {
                    val intent = Intent(requireContext(), Program1::class.java)
                    startActivity(intent)
                }

                button2.setOnClickListener {
                    val intent = Intent(requireContext(), Program2::class.java)
                    startActivity(intent)
                }

                button3.setOnClickListener {
                    val intent = Intent(requireContext(), Program3::class.java)
                    startActivity(intent)
                }

                button4.setOnClickListener {
                    val intent = Intent(requireContext(), Program4::class.java)
                    startActivity(intent)
                }

                button5.setOnClickListener {
                    val intent = Intent(requireContext(), Program5::class.java)
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