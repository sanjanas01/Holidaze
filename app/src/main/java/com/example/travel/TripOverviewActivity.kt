package com.example.travel

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class TripOverviewActivity : AppCompatActivity() {
    private lateinit var tvFetchPlace: TextView
    private lateinit var tvDate: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trip_overview)

        tvFetchPlace = findViewById(R.id.tvFetchPlace)
        tvDate = findViewById(R.id.tvDate)

        // Retrieve trip details from Intent
        val location = intent.getStringExtra("location")
        val startDate = intent.getStringExtra("startDate")
        val endDate = intent.getStringExtra("endDate")

        if (location != null && startDate != null && endDate != null) {
            tvFetchPlace.text = location
            tvDate.text = "$startDate - $endDate"
        } else {
            tvFetchPlace.text = "No location found"
            tvDate.text = "No dates found"
        }

        findViewById<ImageView>(R.id.imageView15).setOnClickListener {
            startActivity(Intent(this, AddItineraryActivity::class.java))
        }
        findViewById<ImageView>(R.id.imageView16).setOnClickListener {
            startActivity(Intent(this, ItineraryActivity::class.java))
        }
        findViewById<ImageView>(R.id.imageView17).setOnClickListener {
            startActivity(Intent(this, TripExpensesActivity::class.java))
        }
        findViewById<ImageView>(R.id.imageView18).setOnClickListener {
            startActivity(Intent(this, PackingActivity::class.java))
        }

        // Set up bottom navigation
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        BottomNavigation.setupBottomNavigation(this, bottomNavigationView)
    }
}
