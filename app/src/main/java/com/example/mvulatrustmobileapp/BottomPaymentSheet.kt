package com.example.mvulatrustmobileapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
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

        // Update the amount text view
        val amountTextView = view.findViewById<TextView>(R.id.amountTextView)
        amountTextView?.text = amount
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
