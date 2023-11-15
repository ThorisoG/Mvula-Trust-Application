package com.example.mvulatrustmobileapp

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.w3c.dom.Text

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [bottom_payment_sheeet2.newInstance] factory method to
 * create an instance of this fragment.
 */
class bottom_payment_sheeet2 : BottomSheetDialogFragment() {
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
        //return inflater.inflate(R.layout.fragment_bottom_payment_sheeet2, container, false)
        val view = inflater.inflate(R.layout.fragment_bottom_payment_sheeet2, container, false)

        // Retrieve the amount from the arguments //
        val amount = arguments?.getString("amount")

        // Update the amount text view
        val amountTextView = view.findViewById<TextView>(R.id.amountTextView)
        amountTextView?.text = amount
        val donatorsname = view.findViewById<TextView>(R.id.firstName)
        val donatorsurname = view.findViewById<TextView>(R.id.surName)

        val payment = view.findViewById<ImageButton>(R.id.pay)
        payment.setOnClickListener {
            val amount = amountTextView.text.toString().trim()
            val name = donatorsname.text.toString().trim()
            val surname = donatorsurname.text.toString().trim()

            if (amount.isNotEmpty() && name.isNotEmpty() && surname.isNotEmpty())
            {
                // Processing Payment Logic
                Toast.makeText(context, "Processing Payment", Toast.LENGTH_SHORT).show()
                //Making the user be able to insert into the database
                val amount = arguments?.getString("amount")
                // Insert donation into the database
                val donatorsfullname = name +" "+surname
                val db = DatabaseHelper(requireContext())
                val donationInserted = db.insertDonation(donatorsfullname, amount?.toDoubleOrNull() ?: 0.0)
                if (donationInserted)
                {
                    showLoadingDialog()
                }
                else
                {
                    // Handling insertion failure//
                    Toast.makeText(requireContext(), "Donation Payment Failed", Toast.LENGTH_SHORT).show()
                }
            }
            else
            {
                // Error Handling if the user did not put anything on the payment
                if (amount.isEmpty())
                {
                    Toast.makeText(context, "Please provide the donation amount", Toast.LENGTH_SHORT).show()
                }
                else if (name.isEmpty())
                {
                    Toast.makeText(context, "Please enter the donor's first name", Toast.LENGTH_SHORT).show()
                }
                else if (surname.isEmpty())
                {
                    Toast.makeText(context, "Please enter the donor's last name", Toast.LENGTH_SHORT).show()
                }
            }
        }

        /*val payButton = view.findViewById<Button>(R.id.pay)
        payButton.setOnClickListener {
            //Making the user be able to insert into the database
            val amount = arguments?.getString("amount")
            // Insert donation into the database
            val db = DatabaseHelper(requireContext())
            val donationInserted = db.insertDonation(donatorsname.text.toString(), amount?.toDoubleOrNull() ?: 0.0)

            if (donationInserted) {
                showLoadingDialog()
            } else {
                // Handling insertion failure//
                Toast.makeText(requireContext(), "Donation Payment Failed", Toast.LENGTH_SHORT).show()
            }
        }*/

        return view
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
         * @return A new instance of fragment bottom_payment_sheeet2.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            bottom_payment_sheeet2().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}