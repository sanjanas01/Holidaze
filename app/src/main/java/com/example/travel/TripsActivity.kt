package com.example.travel
import JourneyAdapter
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.exampe.travel.Journey
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject

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

        val auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser
        if (currentUser == null) {
            Toast.makeText(this, "User not authenticated", Toast.LENGTH_SHORT).show()
            return
        }

        // Fetching data from Firestore
        db.collection("users").document(currentUser.uid).collection("journeys")
            .get()
            .addOnSuccessListener { result ->
                journeyList.clear()
                for (document in result) {
                    val journey = document.toObject<Journey>()
                    Log.d("TripsActivity", "Journey fetched: $journey")
                    journeyList.add(journey)
                }
                adapter.notifyDataSetChanged()
                if (journeyList.isEmpty()) {
                    Log.d("TripsActivity", "No journeys found in Firestore")
                }
            }
            .addOnFailureListener { exception ->
                Toast.makeText(this@TripsActivity, "Failed to load data", Toast.LENGTH_SHORT).show()
                Log.e("TripsActivity", "Error fetching journeys", exception)
            }

        listView.setOnItemClickListener { _, _, position, _ ->
            val selectedJourney = journeyList[position]
            val intent = Intent(this, TripOverviewActivity::class.java).apply {
                putExtra("location", selectedJourney.location)
                putExtra("startDate", selectedJourney.startDate)
                putExtra("endDate", selectedJourney.endDate)
            }
            startActivity(intent)
        }
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        BottomNavigation.setupBottomNavigation(this, bottomNavigationView)
    }
}
