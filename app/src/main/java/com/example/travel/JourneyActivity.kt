package com.example.travel
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.*
import android.widget.EditText
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class JourneyActivity : AppCompatActivity()
{

    private lateinit var locationEditText: EditText
    private lateinit var whereToButton: Button
    private lateinit var startDateButton: Button
    private lateinit var endDateButton: Button
    private lateinit var startPlanningButton: Button
    private lateinit var firestore: FirebaseFirestore

    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_journey)

        locationEditText = findViewById(R.id.locationEditText)
        whereToButton = findViewById(R.id.whereToButton)
        startDateButton = findViewById(R.id.startDateButton)
        endDateButton = findViewById(R.id.endDateButton)
        startPlanningButton = findViewById(R.id.startPlanningButton)
        firestore = FirebaseFirestore.getInstance()

        whereToButton.setOnClickListener {
            whereToButton.visibility = Button.GONE
            locationEditText.visibility = EditText.VISIBLE
        }

        startDateButton.setOnClickListener {
            showDatePicker { date -> startDateButton.text = date }
        }

        endDateButton.setOnClickListener {
            showDatePicker { date -> endDateButton.text = date }
        }

        startPlanningButton.setOnClickListener {
            val location = locationEditText.text.toString().trim()
            val startDate = startDateButton.text.toString().trim()
            val endDate = endDateButton.text.toString().trim()

            if (location.isEmpty() || startDate == "Start Date" || endDate == "End Date") {
                Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show()
            } else {
                saveJourneyDetails(location, startDate, endDate)
                Toast.makeText(this, "Added Successfully", Toast.LENGTH_SHORT).show()
                navigateToOverviewPage()
            }
        }
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        BottomNavigation.setupBottomNavigation(this, bottomNavigationView)
    }


    private fun showDatePicker(onDateSet: (String) -> Unit) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog =
            DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
                val date = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                onDateSet(date)
            }, year, month, day)

        datePickerDialog.show()
    }

    private fun saveJourneyDetails(location: String, startDate: String, endDate: String) {
        val auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser
        if (currentUser == null) {
            Toast.makeText(this, "User not authenticated", Toast.LENGTH_SHORT).show()
            return
        }

        // Create journey details map
        val journeyDetails = hashMapOf(
            "location" to location,
            "startDate" to startDate,
            "endDate" to endDate
        )
        db.collection("journeys").document(currentUser.uid).collection("trips")
            .add(journeyDetails)
            .addOnSuccessListener { documentReference ->
                Toast.makeText(this, "Journey details saved", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                Toast.makeText(
                    this,
                    "Error saving journey details: ${e.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
    }

    private fun navigateToOverviewPage() {
        val intent = Intent(this, TripOverviewActivity::class.java)
        startActivity(intent)
    }
}