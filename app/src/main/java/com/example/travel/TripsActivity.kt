package com.example.travel

import JourneyAdapter
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.exampe.travel.Journey
import com.google.firebase.firestore.FirebaseFirestore

class TripsActivity : AppCompatActivity() {
    private lateinit var listView: ListView
    private lateinit var adapter: JourneyAdapter
    private val journeyList = mutableListOf<Journey>()
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trips)

        listView = findViewById(R.id.list_view)
        adapter = JourneyAdapter(this, journeyList)
        listView.adapter = adapter

        // Fetching data from Firestore
        db.collection("journeys")
            .get()
            .addOnSuccessListener { result ->
                journeyList.clear()
                for (document in result) {
                    val journey = document.toObject<Journey>(Journey::class.java)
                    journeyList.add(journey)
                }
                adapter.notifyDataSetChanged()
            }
            .addOnFailureListener { exception ->
                Toast.makeText(this@TripsActivity, "Failed to load data", Toast.LENGTH_SHORT).show()
            }
    }
}
