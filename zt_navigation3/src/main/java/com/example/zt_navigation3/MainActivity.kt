package com.example.zt_navigation3

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.zt_navigation3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    private var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        val navController = navHostFragment.navController
        val bottomNav = binding?.navView
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.fullFragment) {
                bottomNav?.visibility = View.GONE
            } else {
                bottomNav?.visibility = View.VISIBLE
            }
        }
        bottomNav?.setupWithNavController(navController)

        handleIntentExtras(intent) // Handle initial intent
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }


    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent) // Update the activity's intent
        handleIntentExtras(intent) // Handle new intent
    }

    private fun handleIntentExtras(intent: Intent?) {
        intent?.getIntExtra("navigateToDestinationId", 0)?.takeIf { it != 0 }
            ?.let { destinationId ->
                if (navController?.currentDestination?.id != destinationId) {
                    navController?.navigate(destinationId)
                }
                // Clear the extra to prevent re-navigation
                getIntent().removeExtra("navigateToDestinationId")
            }
    }
}