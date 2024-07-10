package com.example.travel
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

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


//        findViewById<ImageView>(R.id.imageView15).setOnClickListener {
//            startActivity(Intent(this, AddPlaceActivity::class.java))
//        }
        findViewById<ImageView>(R.id.imageView16).setOnClickListener {
            startActivity(Intent(this, ItineraryActivity::class.java))
        }
        findViewById<ImageView>(R.id.imageView17).setOnClickListener {
            startActivity(Intent(this, TripExpensesActivity::class.java))
        }
        findViewById<ImageView>(R.id.imageView18).setOnClickListener {
            startActivity(Intent(this, PackingActivity::class.java))
        }
//        findViewById<ImageView>(R.id.imageView19).setOnClickListener {
//            startActivity(Intent(this, MapsActivity::class.java))
//        }
//        findViewById<ImageView>(R.id.imageView20).setOnClickListener {
//            startActivity(Intent(this, NotesActivity::class.java))
//        }
//        findViewById<ImageView>(R.id.imageView21).setOnClickListener {
//            startActivity(Intent(this, CollaboratorsActivity::class.java))
//        }
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        BottomNavigation.setupBottomNavigation(this, bottomNavigationView)
    }

//    private fun fetchTripDetails(userId: String, location: String, startDate: String, endDate: String) {
//        val db = FirebaseFirestore.getInstance()
//
//        db.collection("journeys").document(userId).collection("trips")
//            .whereEqualTo("location", location)
//            .whereEqualTo("startDate", startDate)
//            .whereEqualTo("endDate", endDate)
//            .get()
//            .addOnSuccessListener { querySnapshot ->
//                if (!querySnapshot.isEmpty) {
//                    for (document in querySnapshot.documents) {
//                        val location = document.getString("location")
//                        val startDate = document.getString("startDate")
//                        val endDate = document.getString("endDate")
//                        tvFetchPlace.text = location
//                        tvDate.text = "$startDate - $endDate"
//                    }
//                } else {
//                    tvFetchPlace.text = "No trips found"
//                    tvDate.text = "No dates found"
//                }
//            }
//            .addOnFailureListener { e ->
//                // Handle any errors that might occur
//                Toast.makeText(
//                    this,
//                    "Error fetching journey details: ${e.message}",
//                    Toast.LENGTH_SHORT
//                ).show()
//                tvFetchPlace.text = "Error fetching trips"
//                tvDate.text = "Error fetching dates"
//            }
//
//
//    }
}


