package com.example.travel

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CalendarView
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AddExpenseActivity : AppCompatActivity() {

    private lateinit var calendarView: CalendarView
    private lateinit var tvSelectedDate: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_expense)

        calendarView = findViewById(R.id.calendarView)
        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        val etAmount = findViewById<EditText>(R.id.etAmount)
        val etNote = findViewById<EditText>(R.id.etNote)
        val btnAddExpense = findViewById<Button>(R.id.btnAddExpense)

        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            // Handle date change
            val selectedDate = "${dayOfMonth}/${month + 1}/${year}" // Example date format
            tvSelectedDate.text = "Selected Date: $selectedDate"
            tvSelectedDate.visibility = View.VISIBLE
        }

        btnAddExpense.setOnClickListener {
            // Handle add expense button click
            // Example validation
            val amount = etAmount.text.toString().toIntOrNull()
            val note = etNote.text.toString()

            if (amount == null || amount <= 0) {
                Toast.makeText(this, "Please enter a valid amount", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // TODO: Save the expense details (category, amount, date, note)

            Toast.makeText(this, "Expense added", Toast.LENGTH_SHORT).show()
            finish() // Close the activity
        }
    }
}
