package com.example.mvulatrustmobileapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mvulatrustmobileapp.databinding.ActivityLoginPageBinding

class LoginPage : AppCompatActivity() {
    private var binding: ActivityLoginPageBinding? = null
    private var databaseHelper: DatabaseHelper? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginPageBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        databaseHelper = DatabaseHelper(this)

        binding!!.loginbt.setOnClickListener(View.OnClickListener {
            val email: String = binding!!.Email.getText().toString()
            val password: String = binding!!.Password.getText().toString()
            if (email == "" || password == "") Toast.makeText(
                this@LoginPage,
                "All fields need to be filled",
                Toast.LENGTH_SHORT
            ).show() else {
                val checkCredentials: Boolean =
                    databaseHelper!!.checkEmailandPassword(email, password)
                if (checkCredentials) {
                    Toast.makeText(this@LoginPage, "Login successfully", Toast.LENGTH_SHORT).show()
                    val intent = Intent(applicationContext, MainActivity2::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this@LoginPage, "Oops invalid Credentials", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        })
        binding!!.signRedirect.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@LoginPage, SignUp::class.java)
            startActivity(intent)
        })
    }

    //This is temporary will add things later should the features work //
    fun AdminBeta(view: View)
    {
        // Create an Intent to open SecondActivity//
        val intent = Intent(this, AdminSplashScreen::class.java)
        // Start the SecondActivity
        startActivity(intent)
    }

}