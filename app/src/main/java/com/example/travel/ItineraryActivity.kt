package com.example.travel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView

class ItineraryActivity : AppCompatActivity() {

    private lateinit var tripAdapter: TripAdapter
    private lateinit var tripList: ArrayList<Trip>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_itinerary)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewTrips)
        recyclerView.layoutManager = LinearLayoutManager(this)

        tripList = arrayListOf(
            Trip("29/06/2024", R.drawable.london_eye, "London Eye", "8:00 - 10:00", "Approx cost 20$"),
            Trip("29/06/2024", R.drawable.london_bridge, "London Bridge", "8:00 - 10:00", "Approx cost 20$"),
            Trip("29/06/2024", R.drawable.big_ben, "Big Ben", "8:00 - 10:00", "Approx cost 20$"),
            Trip("30/06/2024", R.drawable.british_museum, "British Museum", "8:00 - 10:00", "Approx cost 20$")
        )

        tripAdapter = TripAdapter(tripList)
        recyclerView.adapter = tripAdapter

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        BottomNavigation.setupBottomNavigation(this, bottomNavigationView)
    }
}
