package com.example.popliticalpreparednesswithusecases

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import com.example.popliticalpreparednesswithusecases.presentation.adapters.ElectionAdapter
import com.example.popliticalpreparednesswithusecases.presentation.adapters.RepresentativeAdapter
import com.example.popliticalpreparednesswithusecases.presentation.viewmodel.ElectionViewModel
import com.example.popliticalpreparednesswithusecases.presentation.viewmodel.ElectionViewModelFactory
import com.example.popliticalpreparednesswithusecases.presentation.viewmodel.RepresentativeViewModel
import com.example.popliticalpreparednesswithusecases.presentation.viewmodel.RepresentativeViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: ElectionViewModelFactory
    @Inject
    lateinit var representativeFactory: RepresentativeViewModelFactory
    @Inject
    lateinit var electionAdapter: ElectionAdapter
    @Inject
    lateinit var representativeAdapter: RepresentativeAdapter
    lateinit var viewModel: ElectionViewModel
    lateinit var representativeViewModel: RepresentativeViewModel
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Retrieve NavController from the NavHostFragment
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController
        // Set up the action bar for use with the NavController
        setupActionBarWithNavController(this, navController)

        viewModel = ViewModelProvider(this, factory)
            .get(ElectionViewModel::class.java)

        representativeViewModel = ViewModelProvider(this, representativeFactory)
            .get(RepresentativeViewModel::class.java)
    }

    /**
     * Handle navigation when the user chooses Up from the action bar.
     */
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}