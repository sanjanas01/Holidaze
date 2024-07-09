package com.example.travel
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MyAccountActivity : AppCompatActivity() {

    private lateinit var textViewName: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_account)

        textViewName = findViewById(R.id.textView4)
        val updateProfileButton = findViewById<Button>(R.id.update_profile_button)

        updateProfileButton.setOnClickListener {
            val intent = Intent(this, UpdateProfileActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_UPDATE_PROFILE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_UPDATE_PROFILE && resultCode == Activity.RESULT_OK) {
            val newName = data?.getStringExtra("NEW_NAME")
            newName?.let {
                textViewName.text = it
            }
        }
    }

    companion object {
        const val REQUEST_CODE_UPDATE_PROFILE = 1
    }
}
