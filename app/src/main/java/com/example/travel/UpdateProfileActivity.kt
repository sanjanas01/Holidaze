package com.example.travel
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class UpdateProfileActivity : AppCompatActivity() {

    private lateinit var newNameEditText: EditText
    private lateinit var newEmailEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        newNameEditText = findViewById(R.id.new_name)
        newEmailEditText = findViewById(R.id.new_email)

        val updateButton: Button = findViewById(R.id.button_update)
        updateButton.setOnClickListener {
            updateProfile()
        }
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        BottomNavigation.setupBottomNavigation(this, bottomNavigationView)
    }

    private fun updateProfile() {
        val newName = newNameEditText.text.toString().trim()
        val newEmail = newEmailEditText.text.toString().trim()
        if (newName.isNotEmpty()) {
            val resultIntent = Intent()
            resultIntent.putExtra("NEW_NAME", newName)
            setResult(RESULT_OK, resultIntent)
            finish()
        }

    }
}