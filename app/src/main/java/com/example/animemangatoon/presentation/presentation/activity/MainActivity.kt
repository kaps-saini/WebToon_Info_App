package com.example.animemangatoon.presentation.presentation.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.animemangatoon.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var btmNavigation:BottomNavigationView
    private lateinit var navController:NavController

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btmNavigation = findViewById(R.id.bottomSheet)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container_view_tag) as NavHostFragment
        navController = navHostFragment.navController

        btmNavigation.setupWithNavController(navController)

        navController.addOnDestinationChangedListener{ _,destination, _ ->
            when(destination.id){
                R.id.dashboard,R.id.favoritesFragment->{
                    btmNavigation.visibility = View.VISIBLE
                }
                else ->{
                    btmNavigation.visibility = View.GONE
                }
            }
        }

        onBackPressedDispatcher.addCallback(this,object :OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                when(navController.currentDestination?.id){
                    R.id.dashboard,R.id.detailedFragment,R.id.favoritesFragment -> {
                        if (!navController.popBackStack()){
                            finish()
                        }
                    }else -> {
                        navController.popBackStack()
                    }
                }
            }

        })
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}