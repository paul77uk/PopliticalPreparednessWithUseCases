package com.example.popliticalpreparednesswithusecases

import com.example.popliticalpreparednesswithusecases.presentation.adapters.RepresentativeAdapter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import com.example.popliticalpreparednesswithusecases.presentation.adapters.ElectionAdapter
import com.example.popliticalpreparednesswithusecases.presentation.viewmodel.*
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: ElectionViewModelFactory

    @Inject
    lateinit var representativeFactory: RepresentativeViewModelFactory

    @Inject
    lateinit var voterInfoViewModelFactory: VoterInfoViewModelFactory

    @Inject
    lateinit var electionAdapter: ElectionAdapter

    @Inject
    lateinit var savedElectionAdapter: ElectionAdapter

    @Inject
    lateinit var representativeAdapter: RepresentativeAdapter

    lateinit var viewModel: ElectionViewModel
    lateinit var representativeViewModel: RepresentativeViewModel
    lateinit var voterInfoViewModel: VoterInfoViewModel

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

        voterInfoViewModel = ViewModelProvider(this, voterInfoViewModelFactory)
        .get(VoterInfoViewModel::class.java)
    }

    /**
     * Handle navigation when the user chooses Up from the action bar.
     */
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}