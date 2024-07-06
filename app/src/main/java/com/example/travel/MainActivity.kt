package com.example.travel

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonMyAccount = findViewById<Button>(R.id.button_my_account)
        buttonMyAccount.setOnClickListener {
            val intent = Intent(this, MyAccountActivity::class.java)
            startActivity(intent)
        }
    }
}
