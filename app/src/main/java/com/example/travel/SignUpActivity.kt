package com.example.travel

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class SignUpActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        // Initialize Firebase Auth and Firestore
        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        val nameEditText: EditText = findViewById(R.id.editTextText)
        val emailEditText: EditText = findViewById(R.id.editTextTextEmailAddress)
        val phoneEditText: EditText = findViewById(R.id.editTextPhone)
        val passwordEditText: EditText = findViewById(R.id.editTextTextPassword)
        val confirmPasswordEditText: EditText = findViewById(R.id.editTextTextPassword2)
        val createButton: Button = findViewById(R.id.button)

        createButton.setOnClickListener {
            val name = nameEditText.text.toString().trim()
            val email = emailEditText.text.toString().trim()
            val phone = phoneEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()
            val confirmPassword = confirmPasswordEditText.text.toString().trim()

            if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password != confirmPassword) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Create user with Firebase Authentication
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val userId = auth.currentUser?.uid

                        val user = hashMapOf(
                            "name" to name,
                            "email" to email,
                            "phone" to phone
                        )

                        // Save additional user details to Firestore
                        db.collection("account").document(userId!!)
                            .set(user)
                            .addOnSuccessListener {
                                Toast.makeText(this, "Account created successfully!", Toast.LENGTH_SHORT).show()
                                val intent = Intent(this, HomeActivity::class.java)
                                startActivity(intent)
                                finish() // Optionally finish the current activity so that the user cannot go back to it
                            }
                            .addOnFailureListener { e ->
                                Toast.makeText(this, "Error saving user details: ${e.message}", Toast.LENGTH_SHORT).show()
                            }
                    } else {
                        Toast.makeText(this, "Authentication failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
}
