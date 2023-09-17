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
        /*val cardView = view.findViewById<CardView>(R.id.cardview3)

        cardView.setOnClickListener {
        }

                val view = inflater.inflate(R.layout.fragment_donation, container, false)

                val payButton = view.findViewById<ImageButton>(R.id.payButton)

                // Find the CardViews
                val cardView1 = view.findViewById<CardView>(R.id.cardview1)
                val cardView2 = view.findViewById<CardView>(R.id.cardview2)
                val cardView3 = view.findViewById<CardView>(R.id.cardview3)
                val cardView4 = view.findViewById<CardView>(R.id.cardview4)
                // Add click listeners to the CardViews
                cardView1.setOnClickListener {
                    // Update the amount text in the BottomFragment
                    val bottomFragment = BottomPaymentSheet()
                    bottomFragment.updateAmount("R50")
                    bottomFragment.show(childFragmentManager, bottomFragment.tag)
                }

                cardView2.setOnClickListener {
                    val bottomFragment = BottomPaymentSheet()
                    bottomFragment.updateAmount("R150")
                    bottomFragment.show(childFragmentManager, bottomFragment.tag)
                }

                cardView3.setOnClickListener {
                    val bottomFragment = BottomPaymentSheet()
                    bottomFragment.updateAmount("R200")
                    bottomFragment.show(childFragmentManager, bottomFragment.tag)
                }

        cardView4.setOnClickListener {
            val bottomFragment = BottomPaymentSheet()
            bottomFragment.updateAmount("R500")
            bottomFragment.show(childFragmentManager, bottomFragment.tag)
        }

                payButton.setOnClickListener {
                    // Handle the pay button click
                }

        val view = inflater.inflate(R.layout.fragment_donation, container, false)

        val payButton = view.findViewById<ImageButton>(R.id.payButton)

        payButton.setOnClickListener {
            val bottomSheetFragment = BottomPaymentSheet()
            bottomSheetFragment.show(childFragmentManager, bottomSheetFragment.tag)
        }
        return view
            // Other code...
        }*/

        /*
        the better code:
        val view = inflater.inflate(R.layout.fragment_donation, container, false)

        val payButton = view.findViewById<ImageButton>(R.id.payButton)

                val cardView1 = view.findViewById<CardView>(R.id.cardview1)
                val cardView2 = view.findViewById<CardView>(R.id.cardview2)
                val cardView3 = view.findViewById<CardView>(R.id.cardview3)
                val cardView4 = view.findViewById<CardView>(R.id.cardview4)

        payButton.setOnClickListener {
            // This is the pay button click listener
            // You can perform any actions related to the pay button here

            // You can also show the BottomPaymentSheet fragment here
            val bottomFragment = BottomPaymentSheet()
            bottomFragment.arguments = Bundle().apply {
                putString("amount", "RXXX") // Replace "RXXX" with the actual amount
            }
            bottomFragment.show(childFragmentManager, bottomFragment.tag)
        }

                cardView1.setOnClickListener {
                    val bottomFragment = BottomPaymentSheet()
                    bottomFragment.arguments = Bundle().apply {
                        putString("amount", "R50") // Replace "R50" with the actual amount
                    /*}
                    bottomFragment.show(childFragmentManager, bottomFragment.tag)*/
                }

                cardView2.setOnClickListener {
                    val bottomFragment = BottomPaymentSheet()
                    bottomFragment.arguments = Bundle().apply {
                        putString("amount", "R150") // Replace "R150" with the actual amount
                    /*}
                    bottomFragment.show(childFragmentManager, bottomFragment.tag)*/
                }

                cardView3.setOnClickListener {
                    val bottomFragment = BottomPaymentSheet()
                    bottomFragment.arguments = Bundle().apply {
                        putString("amount", "R200") // Replace "R200" with the actual amount
                    /*}
                    bottomFragment.show(childFragmentManager, bottomFragment.tag)*/
                }

                cardView4.setOnClickListener {
                    val bottomFragment = BottomPaymentSheet()
                    bottomFragment.arguments = Bundle().apply {
                        putString("amount", "R500") // Replace "R500" with the actual amount
                    /*}
                    bottomFragment.show(childFragmentManager, bottomFragment.tag)*/
                }


                return view


            // ... (other code)
        }*/
                val view = inflater.inflate(R.layout.fragment_donation, container, false)

                val payButton = view.findViewById<ImageButton>(R.id.payButton)

                val cardView1 = view.findViewById<CardView>(R.id.cardview1)
                val cardView2 = view.findViewById<CardView>(R.id.cardview2)
                val cardView3 = view.findViewById<CardView>(R.id.cardview3)
                val cardView4 = view.findViewById<CardView>(R.id.cardview4)

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

                payButton.setOnClickListener {
                    // This is the pay button click listener
                    // You can perform any actions related to the pay button here

                    // Check if an amount is selected, then show the BottomPaymentSheet
                    if (selectedAmount != null) {
                        val bottomFragment = BottomPaymentSheet()
                        bottomFragment.arguments = Bundle().apply {
                            putString("amount", selectedAmount)
                        }
                        bottomFragment.show(childFragmentManager, bottomFragment.tag)
                    }
                }

                return view
            }


        /*
        val cardView1 = view.findViewById<CardView>(R.id.cardview1)
        val cardView2 = view.findViewById<CardView>(R.id.cardview2)
        val cardView3 = view.findViewById<CardView>(R.id.cardview3)
        val cardView4 = view.findViewById<CardView>(R.id.cardview4)

        cardView1.setOnClickListener {
            val bottomFragment = BottomPaymentSheet()
            bottomFragment.updateAmount("R50")
            bottomFragment.show(childFragmentManager, bottomFragment.tag)
        }

        cardView2.setOnClickListener {
            val bottomFragment = BottomPaymentSheet()
            bottomFragment.updateAmount("R150")
            bottomFragment.show(childFragmentManager, bottomFragment.tag)
        }

        cardView3.setOnClickListener {
            val bottomFragment = BottomPaymentSheet()
            bottomFragment.updateAmount("R200")
            bottomFragment.show(childFragmentManager, bottomFragment.tag)
        }

        cardView4.setOnClickListener {
            val bottomFragment = BottomPaymentSheet()
            bottomFragment.updateAmount("R500")
            bottomFragment.show(childFragmentManager, bottomFragment.tag)
        }

        payButton.setOnClickListener {
            // Show the BottomPaymentSheet fragment and pass the amount as an argument
            val bottomFragment = BottomPaymentSheet()
            bottomFragment.arguments = Bundle().apply {
                putString("amount", "RXXX") // Replace "RXXX" with the actual amount
            }
            bottomFragment.show(childFragmentManager, bottomFragment.tag)
        }

        return view*/

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

