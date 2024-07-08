package com.example.travel

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CalendarView
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class AddExpenseActivity : AppCompatActivity() {

    private lateinit var calendarView: CalendarView
    private lateinit var tvSelectedDate: TextView
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_expense)

        calendarView = findViewById(R.id.calendarView)
        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        val etAmount = findViewById<EditText>(R.id.etAmount)
        val etNote = findViewById<EditText>(R.id.etNote)
        val btnAddExpense = findViewById<Button>(R.id.btnAddExpense)
        val radioGroupCategory = findViewById<RadioGroup>(R.id.radioGroupCategory)

        db = FirebaseFirestore.getInstance()

        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val selectedDate = "${dayOfMonth}/${month + 1}/${year}"
            tvSelectedDate.text = "Selected Date: $selectedDate"
            tvSelectedDate.visibility = View.VISIBLE
        }

        btnAddExpense.setOnClickListener {
            val amount = etAmount.text.toString().toIntOrNull()
            val note = etNote.text.toString()
            val selectedDate = tvSelectedDate.text.toString().removePrefix("Selected Date: ")
            val selectedCategory = findViewById<RadioButton>(radioGroupCategory.checkedRadioButtonId)?.text.toString()

            if (amount == null || amount <= 0) {
                Toast.makeText(this, "Please enter a valid amount", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val expense = hashMapOf(
                "amount" to amount,
                "note" to note,
                "date" to selectedDate,
                "category" to selectedCategory
            )

            db.collection("trips").document("trip1").collection("expenses")
                .add(expense)
                .addOnSuccessListener { documentReference ->
                    Toast.makeText(this, "Expense added successfully", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, TripExpensesActivity::class.java)
                    startActivity(intent)
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Error adding expense", Toast.LENGTH_SHORT).show()
                }
        }
    }
}
