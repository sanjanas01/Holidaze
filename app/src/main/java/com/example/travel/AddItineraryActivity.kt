package com.example.travel

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*

class AddItineraryActivity : AppCompatActivity() {

    private lateinit var buttonSelectDate: Button
    private lateinit var buttonSelectTime: Button
    private lateinit var editTextAddPlace: EditText
    private lateinit var buttonSaveItinerary: Button

    private val db = FirebaseFirestore.getInstance()
    private var selectedDate: String = ""
    private var selectedTime: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_itinerary)

        buttonSelectDate = findViewById(R.id.selectDate)
        buttonSelectTime = findViewById(R.id.selectTime)
        editTextAddPlace = findViewById(R.id.addplace)
        buttonSaveItinerary = findViewById(R.id.saveItinerary)

        buttonSelectDate.setOnClickListener {
            showDatePicker { date ->
                selectedDate = date
                buttonSelectDate.text = date
            }
        }

        buttonSelectTime.setOnClickListener {
            showTimePickerDialog()
        }

        buttonSaveItinerary.setOnClickListener {
            saveItineraryToFirebase()
        }
    }

    private fun showDatePicker(onDateSet: (String) -> Unit) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
            val date = "$selectedDay/${selectedMonth + 1}/$selectedYear"
            onDateSet(date)
        }, year, month, day)

        datePickerDialog.show()
    }

    private fun showTimePickerDialog() {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(this, { _, selectedHour, selectedMinute ->
            selectedTime = String.format("%02d:%02d", selectedHour, selectedMinute)
            buttonSelectTime.text = selectedTime
        }, hour, minute, true)

        timePickerDialog.show()
    }

    private fun saveItineraryToFirebase() {
        val place = editTextAddPlace.text.toString()

        if (selectedDate.isEmpty() || selectedTime.isEmpty() || place.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            return
        }

        val itinerary = hashMapOf(
            "date" to selectedDate,
            "time" to selectedTime,
            "place" to place
        )

        db.collection("itineraries")
            .add(itinerary)
            .addOnSuccessListener {
                Toast.makeText(this, "Itinerary added", Toast.LENGTH_SHORT).show()
                // Navigate to HomeActivity after saving
                navigateToHome()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Failed to add itinerary", Toast.LENGTH_SHORT).show()
            }
    }

    private fun navigateToHome() {
        val intent = Intent(this, ItineraryActivity::class.java)
        startActivity(intent)
        finish() // Optional: Close AddItineraryActivity
    }
}