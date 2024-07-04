package com.example.travel

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val emailEditText = findViewById<EditText>(R.id.emailEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        val cityEditText = findViewById<EditText>(R.id.cityEditText)
        val loginButton = findViewById<Button>(R.id.loginButton)
        val forgotPasswordTextView = findViewById<TextView>(R.id.forgotPasswordTextView)
        val signUpTextView = findViewById<TextView>(R.id.signUpTextView)
        val googleSignUpButton = findViewById<Button>(R.id.googleSignUpButton)

        loginButton.setOnClickListener {
            // Handle login logic here
        }

        forgotPasswordTextView.setOnClickListener {
            // Handle forgot password logic here
        }

        signUpTextView.setOnClickListener {
            // Handle sign-up logic here
        }

        googleSignUpButton.setOnClickListener {
            // Handle Google sign-up logic here
        }
    }
}
