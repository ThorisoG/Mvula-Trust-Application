package com.example.mvulatrustmobileapp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.RelativeLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [User_settings.newInstance] factory method to
 * create an instance of this fragment.
 */
class User_settings : Fragment() {
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
        return inflater.inflate(R.layout.fragment_user_settings, container, false)

        val rootView = inflater.inflate(R.layout.fragment_user_settings, container, false)

        val logout = rootView.findViewById<RelativeLayout>(R.id.relativeLayout7)

        logout.setOnClickListener {
            val intent = Intent(requireContext(), LoginPage::class.java)
            startActivity(intent)
        }

        //To transition to the user settings account
        val next1 = rootView.findViewById<RelativeLayout>(R.id.next1)
        next1.setOnClickListener {
            val intent2 =Intent(requireContext(),UserCustomize::class.java)
            requireContext().startActivity(intent2)
        }

        //the floating chat button
        val chatButton = rootView.findViewById<FloatingActionButton>(R.id.chatbutton)
        chatButton.setOnClickListener {
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
         * @return A new instance of fragment User_settings.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            User_settings().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}