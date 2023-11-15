package com.example.mvulatrustmobileapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Donation.newInstance] factory method to
 * create an instance of this fragment.
 */
class Donation : Fragment() {
    //var bottomFragment = BottomFragment1();
    private var selectedAmount: String? = null
    private var selectedCardView: CardView? = null

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

                val view = inflater.inflate(R.layout.fragment_donation, container, false)

                val payButton = view.findViewById<ImageButton>(R.id.payButton)

                val cardView1 = view.findViewById<CardView>(R.id.cardview1)
                val cardView2 = view.findViewById<CardView>(R.id.cardview2)
                val cardView3 = view.findViewById<CardView>(R.id.cardview3)
                val cardView4 = view.findViewById<CardView>(R.id.cardview4)
        //mastercard
        val cardView5 = view.findViewById<CardView>(R.id.cardview5)

        //paypal//
        val cardView6 = view.findViewById<CardView>(R.id.cardview7)

        val amount = view.findViewById<EditText>(R.id.amount)

                cardView1.setOnClickListener {
                    // Set the selected amount when cardView1 is clicked
                    selectedAmount = "10.00"
                    amount.setText(selectedAmount)
                }

                cardView2.setOnClickListener {
                    // Set the selected amount when cardView2 is clicked
                    selectedAmount = "50.00"
                    amount.setText(selectedAmount)
                }

                cardView3.setOnClickListener {
                    // Set the selected amount when cardView3 is clicked
                    selectedAmount = "100.00"
                    amount.setText(selectedAmount)
                }

                cardView4.setOnClickListener {
                    // Set the selected amount when cardView4 is clicked
                    selectedAmount = "200.00"
                    amount.setText(selectedAmount)
                }
        cardView5.setOnClickListener {
            // This is master card //
            selectedCardView = cardView5
            if (selectedCardView != null) {
                Toast.makeText(requireContext(), "MasterCard selected", Toast.LENGTH_SHORT).show()
            }
            else
            {
                Toast.makeText(requireContext(), "You Have not selected a payment option", Toast.LENGTH_SHORT).show()
            }
        }

        cardView6.setOnClickListener {
            // This is paypal
            selectedCardView = cardView6
            if (selectedCardView != null) {
                Toast.makeText(requireContext(), "PayPal selected", Toast.LENGTH_SHORT).show()
            }
            else
            {
                Toast.makeText(requireContext(), "You Have not selected a payment option", Toast.LENGTH_SHORT).show()
            }
        }


        payButton.setOnClickListener {
            val enteredAmount = amount.text.toString().trim()

            if (selectedCardView == cardView5 || selectedCardView == cardView6) {
                // User selected a preselected amount
                val amountToSend = if (enteredAmount.isNotBlank() && enteredAmount.toDouble() > 0) {
                    // Use the custom amount if entered and non-zero
                    enteredAmount
                } else if (selectedAmount != null && selectedAmount!!.toDouble() > 0) {
                    // Use the preselected amount if available and non-zero
                    selectedAmount
                } else {
                    // Handle the case where neither a preselected nor custom amount is valid
                    Toast.makeText(requireContext(), "Please enter a Number above zero", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                if (selectedCardView == cardView5) {
                    val bottomFragment = BottomPaymentSheet()
                    bottomFragment.arguments = Bundle().apply {
                        putString("amount", amountToSend)
                    }
                    bottomFragment.show(childFragmentManager, bottomFragment.tag)
                }
                if (selectedCardView == cardView6) {
                    val bottomFragment = bottom_payment_sheeet2()
                    bottomFragment.arguments = Bundle().apply {
                        putString("amount", amountToSend)
                    }
                    bottomFragment.show(childFragmentManager, bottomFragment.tag)
                }
            }
            else
            {
                // Handle the case where neither a preselected nor custom amount is available
                if (selectedCardView == null) {
                    // This is for if the user didn't select a payment method
                    Toast.makeText(requireContext(), "Please select an amount & payment method", Toast.LENGTH_SHORT).show()
                }
                else
                {
                    // This is for if the user entered zero or an invalid amount
                    Toast.makeText(requireContext(), "Please enter a valid non-zero donation amount", Toast.LENGTH_SHORT).show()
                }
            }
        }
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Donation.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Donation().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}

