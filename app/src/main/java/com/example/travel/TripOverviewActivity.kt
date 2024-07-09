package com.example.travel
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class TripOverviewActivity : AppCompatActivity()
{
    private lateinit var tvFetchPlace: TextView
    private lateinit var tvDate: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trip_overview)
        tvFetchPlace = findViewById(R.id.tvFetchPlace)
        tvDate = findViewById(R.id.tvDate)
        fetchTripDetails()

//        findViewById<ImageView>(R.id.imageView15).setOnClickListener {
//            startActivity(Intent(this, AddPlaceActivity::class.java))
//        }

//        findViewById<ImageView>(R.id.imageView18).setOnClickListener {
//            startActivity(Intent(this, PackingListActivity::class.java))
//        }
//        findViewById<ImageView>(R.id.imageView19).setOnClickListener {
//            startActivity(Intent(this, MapsActivity::class.java))
//        }
//        findViewById<ImageView>(R.id.imageView20).setOnClickListener {
//            startActivity(Intent(this, NotesActivity::class.java))
//        }
//        findViewById<ImageView>(R.id.imageView21).setOnClickListener {
//            startActivity(Intent(this, CollaboratorsActivity::class.java))
//        }

    }
    private fun fetchTripDetails() {
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        val db = FirebaseFirestore.getInstance()
        db.collection("journeys")
            .document(userId!!)
            .get()
            .addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot.exists()) {
                    val location = documentSnapshot.getString("location")
                    val startDate = documentSnapshot.getString("startDate")
                    val endDate = documentSnapshot.getString("endDate")

                    // Set text to TextViews
                    tvFetchPlace.text = location
                    tvDate.text = "$startDate - $endDate"
                } else {
                    // Handle case where no trip data is found for the user
                    tvFetchPlace.text = "No trip found"
                    tvDate.text = "No date found"
                }
            }
            .addOnFailureListener { exception ->
                // Handle failures
                tvFetchPlace.text = "Error fetching trip"
                tvDate.text = ""
            }
    }
}