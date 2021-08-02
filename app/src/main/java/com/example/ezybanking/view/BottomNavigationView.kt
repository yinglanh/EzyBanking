package com.example.ezybanking.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.example.ezybanking.R
import com.google.android.material.bottomnavigation.BottomNavigationView


class BottomNavigationView : AppCompatActivity() {

    private val TAG = "BottomNavigationView"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_navigation_main)
        val navController: NavController = Navigation.findNavController(this, R.id.nav_host_fragment)
        findViewById<BottomNavigationView>(R.id.bottom_navigation).setupWithNavController(navController)
    }
}

















