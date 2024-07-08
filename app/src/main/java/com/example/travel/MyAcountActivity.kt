package com.example.travel

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MyAccountActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_account)
    }

    fun goToUpdateProfile(view: View) {
        val intent = Intent(this, UpdateProfileActivity::class.java)
        startActivity(intent)
    }
}
