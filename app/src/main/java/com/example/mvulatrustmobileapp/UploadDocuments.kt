package com.example.mvulatrustmobileapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.example.mvulatrustmobileapp.databinding.ActivityUploadDocumentsBinding

class UploadDocuments : AppCompatActivity() {

    private  lateinit var binding: ActivityUploadDocumentsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUploadDocumentsBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_upload_documents)

        binding.floatingActionButton.setOnClickListener {
            launcher.launch("application/pdf")
        }
    }
    private val launcher = registerForActivityResult(
        ActivityResultContracts.GetContent()

    ){ uri ->

        uri?.let {
            binding.pdfview.fromUri(it)
        }

    }
}