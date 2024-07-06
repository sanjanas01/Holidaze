package com.example.travel
import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class JourneyActivity : AppCompatActivity() {

    private lateinit var whereToButton: Button
    private lateinit var startDateButton: Button
    private lateinit var endDateButton: Button
    private lateinit var startPlanningButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_journey)

        whereToButton = findViewById(R.id.whereToButton)
        startDateButton = findViewById(R.id.startDateButton)
        endDateButton = findViewById(R.id.endDateButton)
        startPlanningButton = findViewById(R.id.startPlanningButton)

        whereToButton.setOnClickListener {
            Toast.makeText(this, "Where To? button clicked", Toast.LENGTH_SHORT).show()
        }

        startDateButton.setOnClickListener {
            showDatePicker { date -> startDateButton.text = date }
        }

        endDateButton.setOnClickListener {
            showDatePicker { date -> endDateButton.text = date }
        }

        startPlanningButton.setOnClickListener {
            Toast.makeText(this, "Start Planning button clicked", Toast.LENGTH_SHORT).show()
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
}
