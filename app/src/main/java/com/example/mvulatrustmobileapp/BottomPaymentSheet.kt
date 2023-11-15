package com.example.mvulatrustmobileapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BottomPaymentSheet.newInstance] factory method to
 * create an instance of this fragment.
 */
//class BottomPaymentSheet : Fragment() {
    class BottomPaymentSheet : BottomSheetDialogFragment() {
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
        val view = inflater.inflate(R.layout.fragment_bottom_payment_sheet, container, false)

        // Retrieve the amount from the arguments
        val amount = arguments?.getString("amount")

        // Update the amount text view //
        val amountTextView = view.findViewById<TextView>(R.id.amountTextView)
        amountTextView?.text = amount
        val cardholder = view.findViewById<EditText>(R.id.Names)
        val cardnum = view.findViewById<EditText>(R.id.cardNo)
        val expirynum = view.findViewById<EditText>(R.id.cellno)
        val cvc = view.findViewById<EditText>(R.id.email)

        val payments = view.findViewById<ImageButton>(R.id.pay)
        payments.setOnClickListener {

            val amount = amountTextView.text.toString().trim()
            val holderName = cardholder.text.toString().trim()
            val cardNumber = cardnum.text.toString().trim()
            val expiryNumber = expirynum.text.toString().trim()
            val cvcNumber = cvc.text.toString().trim()

            if (amount.isEmpty())
            {
                Toast.makeText(requireContext(), "Please provide the donation amount", Toast.LENGTH_SHORT).show()
            }
            else if (holderName.isEmpty())
            {
                Toast.makeText(requireContext(), "Please enter the cardholder's name", Toast.LENGTH_SHORT).show()
            }
            else if (cardNumber.isEmpty() || cardNumber.length != 16)
            {
                Toast.makeText(requireContext(), "Please enter a valid 16-digit card number", Toast.LENGTH_SHORT).show()
            }
            else if (expiryNumber.isEmpty())
            {
                Toast.makeText(requireContext(), "Please enter the expiry date", Toast.LENGTH_SHORT).show()
            }
            else if (cvcNumber.isEmpty() || cvcNumber.length != 3)
            {
                Toast.makeText(requireContext(), "Please enter a valid 3-digit CVV number", Toast.LENGTH_SHORT).show()
            }
            else
            {
                // if all the fields meet the required criteria then donation can occur//
                Toast.makeText(requireContext(), "Processing Payment", Toast.LENGTH_SHORT).show()
                // Insert donation into the database
                val db = DatabaseHelper(requireContext())
                val donationInserted = db.insertDonation(cardholder.text.toString(), amount?.toDoubleOrNull() ?: 0.0)

                if (donationInserted)
                {
                    //if the user has successfully donated//
                    showLoadingDialog()
                }
                else
                {
                    // Handling insertion failure//
                    Toast.makeText(requireContext(), "Donation Payment Failed", Toast.LENGTH_SHORT).show()
                }
            }
        }
        return view
    }

    fun updateAmount(amount: String) {
        val amount1 = view?.findViewById<TextView>(R.id.textView1)
        val amount2 = view?.findViewById<TextView>(R.id.textView2)
        val amount3 = view?.findViewById<TextView>(R.id.textView3)
        val amount4 = view?.findViewById<TextView>(R.id.textView4)
        amount1?.text = amount
        amount2?.text = amount
        amount3?.text = amount
        amount4?.text = amount
        }

    private fun showLoadingDialog() {
        val builder = AlertDialog.Builder(requireContext())
        val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.loading_dialog, null)
        val progressBar = dialogView.findViewById<ProgressBar>(R.id.progressBar)
        val statusTextView = dialogView.findViewById<TextView>(R.id.statusTextView)

        builder.setView(dialogView)
        builder.setCancelable(false)
        val dialog = builder.create()
        dialog.show()

        // Simulate payment processing (You can replace this with your actual payment logic)
        Handler(Looper.getMainLooper()).postDelayed({
            // Update the dialog to show "Payment Complete" and a tick
            progressBar.visibility = View.INVISIBLE
            statusTextView.text = "Payment Complete ✓"

            // Dismiss the dialog after a delay (you can adjust the delay as needed)
            Handler(Looper.getMainLooper()).postDelayed({
                dialog.dismiss()
            }, 1000)  // 1000ms delay (1 second)
        }, 3000)  // Simulate payment processing for 3000ms (3 seconds)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BottomPaymentSheet.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BottomPaymentSheet().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
