package com.example.mvulatrustmobileapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomFragment1 : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_bottom_payment_sheet,container,false)
        val views = inflater.inflate(R.layout.fragment_bottom_payment_sheeet2,container,false)
        return view
        return  views

    }
}



