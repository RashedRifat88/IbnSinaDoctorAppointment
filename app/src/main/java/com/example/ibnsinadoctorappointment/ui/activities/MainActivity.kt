package com.example.ibnsinadoctorappointment.ui.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.ibnsinadoctorappointment.R


class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.findNavController()

//        appBarConfiguration = AppBarConfiguration(
//            setOf(androidx.navigation.ui.R.id.homeFragment, androidx.navigation.ui.R.id.searchFragment, androidx.navigation.ui.R.id.settingsFragment),
//            drawer_layout
//        )
//
//        setSupportActionBar(toolbar)
//        setupActionBarWithNavController(navController, appBarConfiguration)
//
//        bottom_nav.setupWithNavController(navController)
//        nav_view.setupWithNavController(navController)
    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(androidx.navigation.ui.R.menu.options_menu, menu)
//        return true
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return if (item.itemId == androidx.navigation.ui.R.id.termsAndConditions) {
//            val action = TermConditionFragmentDirections.actionGlobalTermConditionFragment()
//            navController.navigate(action)
//            true
//        } else {
//            item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
//        }
//    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }


}