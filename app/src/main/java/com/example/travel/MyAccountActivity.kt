package com.example.travel

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MyAccountActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_account)

        val editTextName = findViewById<EditText>(R.id.edit_text_name)
        val editTextEmail = findViewById<EditText>(R.id.edit_text_email)
        val buttonUpdate = findViewById<Button>(R.id.button_update)

        buttonUpdate.setOnClickListener {
            val name = editTextName.text.toString()
            val email = editTextEmail.text.toString()

            // Here, you can add code to update the user details in your database or backend
            // For demonstration, we'll just show a toast message
            if (name.isNotEmpty() && email.isNotEmpty()) {
                Toast.makeText(this, "Details Updated", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
