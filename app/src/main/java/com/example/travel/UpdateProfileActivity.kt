package com.example.travel
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class UpdateProfileActivity : AppCompatActivity() {

    private lateinit var newNameEditText: EditText
    private lateinit var newEmailEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        newNameEditText = findViewById(R.id.edit_text_new_name)
        newEmailEditText = findViewById(R.id.edit_text_new_email)

        val updateButton: Button = findViewById(R.id.button_update)
        updateButton.setOnClickListener {
            updateProfile()
        }
    }

    private fun updateProfile() {
        val newName = newNameEditText.text.toString().trim()
        val newEmail = newEmailEditText.text.toString().trim()

        // Perform any necessary validation before updating
        if (newName.isNotEmpty()) {
            // Pass the new name back to MyAccountActivity
            val resultIntent = Intent()
            resultIntent.putExtra("NEW_NAME", newName)
            setResult(RESULT_OK, resultIntent)
            finish()
        }

        // Optionally, update other fields like email
        // Example:
        // if (newEmail.isNotEmpty()) {
        //     // Update the email
        // }
    }
}
