package com.example.travel

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.button.MaterialButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class TripExpensesActivity : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore
    private lateinit var tvTotalAmount: TextView
    private lateinit var tvHotelAmount: TextView
    private lateinit var tvFlightAmount: TextView
    private lateinit var tvRestaurantAmount: TextView
    private lateinit var tvSiteSeeingAmount: TextView
    private lateinit var tvTaxiAmount: TextView
    private lateinit var tvOthersAmount: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trip_expenses)

        db = FirebaseFirestore.getInstance()
        tvTotalAmount = findViewById(R.id.tvTotalAmount)
        tvFlightAmount = findViewById(R.id.tvFlightAmount)
        tvRestaurantAmount = findViewById(R.id.tvRestaurantAmount)
        tvSiteSeeingAmount = findViewById(R.id.tvSightSeeingAmount)
        tvTaxiAmount = findViewById(R.id.tvTaxiAmount)
        tvOthersAmount = findViewById(R.id.tvOtherAmount)
        tvHotelAmount = findViewById(R.id.tvHotelAmount)

        findViewById<MaterialButton>(R.id.button).setOnClickListener {
            val intent = Intent(this, AddExpenseActivity::class.java)
            startActivity(intent)
        }
        fetchExpenses()
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        BottomNavigation.setupBottomNavigation(this, bottomNavigationView)
    }

    private fun fetchExpenses() {
        val auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser
        if (currentUser != null) {
            db.collection("users").document(currentUser.uid).collection("expenses")
                .get()
                .addOnSuccessListener { documents ->
                    var totalAmount = 0
                    val categoryAmounts = mutableMapOf(
                        "Hotel" to 0,
                        "Flight" to 0,
                        "Restaurant" to 0,
                        "Site seeing" to 0,
                        "Taxi" to 0,
                        "Others" to 0
                    )

                    for (document in documents) {
                        val amount = document.getLong("amount")?.toInt() ?: 0
                        val category = document.getString("category") ?: ""
                        totalAmount += amount
                        if (category in categoryAmounts) {
                            categoryAmounts[category] = categoryAmounts[category]!! + amount
                        }
                    }

                    tvTotalAmount.text = "₹$totalAmount"
                    tvHotelAmount.text = "₹${categoryAmounts["Hotel"]}"
                    tvFlightAmount.text = "₹${categoryAmounts["Flight"]}"
                    tvRestaurantAmount.text = "₹${categoryAmounts["Restaurant"]}"
                    tvSiteSeeingAmount.text = "₹${categoryAmounts["Site seeing"]}"
                    tvTaxiAmount.text = "₹${categoryAmounts["Taxi"]}"
                    tvOthersAmount.text = "₹${categoryAmounts["Others"]}"
                }
                .addOnFailureListener { exception ->
                    Toast.makeText(this, "Error fetching expenses: ${exception.message}", Toast.LENGTH_SHORT).show()
                }
        } else {
            Toast.makeText(this, "No user logged in", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onResume() {
        super.onResume()
        fetchExpenses()
    }
}
