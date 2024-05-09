package com.iwex.mobilepartsshopstaff

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.iwex.mobilepartsshopstaff.presentation.OnLoggedInListener
import com.iwex.mobilepartsshopstaff.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), OnLoggedInListener {

    private val viewModel: MainViewModel by viewModels()

    private lateinit var navController: NavController
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initViews()
        initNavigation()
        observeViewModel()
    }

    private fun initViews() {
        bottomNavigationView = findViewById(R.id.bottomNavigationView)
    }

    private fun initNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment
        navController = navHostFragment.navController
        bottomNavigationView.setupWithNavController(navController)
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.profileFragment -> {
                    navController.navigate(R.id.profileFragment)
                    true
                }
                R.id.managementFragment -> {
                    navController.navigate(R.id.managementFragment)
                    true
                }
                R.id.settingsFragment -> {
                    navController.navigate(R.id.settingsFragment)
                    true
                }
                else -> false
            }
        }
    }

    private fun observeViewModel() {
        viewModel.isUserAuthenticated.observe(this) { isAuthenticated ->
            if (isAuthenticated) {
                navigateToManagementFragment()
            } else {
                navigateToLoginFragment()
            }
        }
        viewModel.checkUserAuthentication()
    }

    private fun navigateToManagementFragment() {
        navController.popBackStack()
        setBottomNavigationViewVisible()
        navController.navigate(R.id.managementFragment)
    }

    private fun navigateToLoginFragment() {
        navController.popBackStack()
        navController.navigate(R.id.loginFragment)
    }

    private fun setBottomNavigationViewVisible() {
        bottomNavigationView.visibility = View.VISIBLE
    }

    override fun onLoggedIn() {
        navigateToManagementFragment()
    }
}