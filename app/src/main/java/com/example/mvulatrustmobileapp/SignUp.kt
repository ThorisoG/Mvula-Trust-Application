package com.example.mvulatrustmobileapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Toast
import com.example.mvulatrustmobileapp.databinding.ActivitySignUpBinding

class SignUp : AppCompatActivity() {
    private var binding: ActivitySignUpBinding? = null
    private var databaseHelper: DatabaseHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        databaseHelper = DatabaseHelper(this)

        binding!!.btnSignUp.setOnClickListener {
            val email = binding!!.Email.text.toString()
            val password = binding!!.Password.text.toString()
            val confirmPassword = binding!!.confirmPassword.text.toString()

            if (email == "" || password == "" || confirmPassword == "") {
                Toast.makeText(
                    this@SignUp,
                    "All fields are mandatory",
                    Toast.LENGTH_SHORT
                ).show()
            } else if (!isValidEmail(email)) {
                Toast.makeText(
                    this@SignUp,
                    "Invalid email address",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                if (password == confirmPassword) {
                    val checkUserEmail = databaseHelper!!.checkEmail(email)
                    if (!checkUserEmail) {
                        val insert = databaseHelper!!.insertData(email, password)
                        if (insert) {
                            Toast.makeText(
                                this@SignUp,
                                "Signup Successfully!!!",
                                Toast.LENGTH_SHORT
                            ).show()
                            val intent = Intent(applicationContext, LoginPage::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(
                                this@SignUp,
                                "Signup Failed!!!",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } else {
                        Toast.makeText(
                            this@SignUp,
                            "User already exists, please login!!!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    Toast.makeText(
                        this@SignUp,
                        "Oops invalid password!!!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        binding!!.loginRedirectText.setOnClickListener {
            val intent = Intent(applicationContext, LoginPage::class.java)
            startActivity(intent)
        }
    }

    private fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() &&
                email.length > 6 && email.contains("@")
    }
}
