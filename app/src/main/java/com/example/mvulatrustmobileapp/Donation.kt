package com.example.mvulatrustmobileapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
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
        //paypal
        val cardView7 = view.findViewById<CardView>(R.id.cardview7)

                cardView1.setOnClickListener {
                    // Set the selected amount when cardView1 is clicked
                    selectedAmount = "R50"
                }

                cardView2.setOnClickListener {
                    // Set the selected amount when cardView2 is clicked
                    selectedAmount = "R150"
                }

                cardView3.setOnClickListener {
                    // Set the selected amount when cardView3 is clicked
                    selectedAmount = "R200"
                }

                cardView4.setOnClickListener {
                    // Set the selected amount when cardView4 is clicked
                    selectedAmount = "R500"
                }
        cardView5.setOnClickListener {
            // Set the selected amount when cardView5 is clicked
            selectedCardView = cardView5
        }

        cardView7.setOnClickListener {
            // Set the selected amount when cardView7 is clicked
            selectedCardView = cardView7
        }


        payButton.setOnClickListener {
                        if (selectedAmount != null) {
                            if (selectedCardView == cardView5) {
                                val bottomFragment = BottomPaymentSheet()
                                bottomFragment.arguments = Bundle().apply {
                                    putString("amount", selectedAmount)
                                }
                                bottomFragment.show(childFragmentManager, bottomFragment.tag)
                            } else {
                                val bottomFragment = bottom_payment_sheeet2()
                                bottomFragment.arguments = Bundle().apply {
                                    putString("amount", selectedAmount)
                                }
                                bottomFragment.show(childFragmentManager, bottomFragment.tag)
                            }
                        }
                    }

                    return view
                }
                        // Check if an amount is selected, then show the BottomPaymentSheet
                        /*if (selectedAmount != null) {
                        /*val bottomFragment = BottomPaymentSheet()
                        bottomFragment.arguments = Bundle().apply {
                            putString("amount", selectedAmount)
                        }*/
                        val bottomFragment = if (selectedCardView == cardView5) {
                            BottomPaymentSheet()
                        } else {
                            bottom_payment_sheeet2()
                        }

                        bottomFragment.arguments = Bundle().apply {
                            putString("amount", selectedAmount)
                        }
                    }
                        bottomFragment.show(childFragmentManager, bottomFragment.tag)*/

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

