package com.example.travel
import android.app.Activity
import android.content.Intent
import com.google.android.material.bottomnavigation.BottomNavigationView


object BottomNavigation{
    fun setupBottomNavigation(activity: Activity, bottomNavigationView: BottomNavigationView) {
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    val intent = Intent(activity, HomeActivity::class.java)
                    activity.startActivity(intent)
                    true
                }

                R.id.nav_add -> {
                    val intent = Intent(activity, JourneyActivity::class.java)
                    activity.startActivity(intent)
                    true
                }

                R.id.nav_profile -> {
                    val intent = Intent(activity, MyAccountActivity::class.java)
                    activity.startActivity(intent)
                    true
                }

                else -> false
            }
        }
    }
}
