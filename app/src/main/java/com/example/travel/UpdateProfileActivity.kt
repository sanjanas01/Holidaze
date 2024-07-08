package com.example.travel

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class UpdateProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        val editTextNewName = findViewById<EditText>(R.id.edit_text_new_name)
        val editTextNewEmail = findViewById<EditText>(R.id.edit_text_new_email)
        val buttonUpdate = findViewById<Button>(R.id.button_update)

        buttonUpdate.setOnClickListener {
            val newName = editTextNewName.text.toString()
            val newEmail = editTextNewEmail.text.toString()

            // Here you can implement your logic to update the profile details
            // For now, let's just display a toast message
            if (newName.isNotEmpty() && newEmail.isNotEmpty()) {
                // Update profile logic goes here
                Toast.makeText(this, "Profile Updated", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
