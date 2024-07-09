package com.example.travel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val viewPager: ViewPager2 = findViewById(R.id.viewPager)
        val images = listOf(
            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image3
        )
        val adapter = ImagePagerAdapter(images)
        viewPager.adapter = adapter

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    // Handle home action
                    true
                }
                R.id.nav_add -> {
                    // Handle add action
                    true
                }
                R.id.nav_profile -> {
                    // Handle profile action
                    true
                }
                else -> false
            }
        }
    }
}
