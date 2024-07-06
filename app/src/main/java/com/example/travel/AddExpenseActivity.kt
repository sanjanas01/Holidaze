package com.example.travel

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class AddExpenseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_expense)

        val etAmount = findViewById<EditText>(R.id.etAmount)
        val etNote = findViewById<EditText>(R.id.etNote)
        val btnAddExpense = findViewById<Button>(R.id.btnAddExpense)

        btnAddExpense.setOnClickListener {
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
