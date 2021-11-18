package com.example.popliticalpreparednesswithusecases

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.popliticalpreparednesswithusecases.databinding.FragmentLaunchBinding


// import com.example.android.politicalpreparedness.election.adapter.ElectionListAdapter
// import com.example.android.politicalpreparedness.election.adapter.ElectionListener

class LaunchFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        return inflater.inflate(R.layout.fragment_launch, container, false)
        val binding = FragmentLaunchBinding.inflate(inflater)
//        binding.lifecycleOwner = this

        binding.representativeButton.setOnClickListener { navToRepresentatives() }
        binding.upcomingButton.setOnClickListener { navToElections() }

        return binding.root
    }

    private fun navToElections() {
        this.findNavController()
            .navigate(LaunchFragmentDirections.actionLaunchFragmentToElectionFragment())
    }

    private fun navToRepresentatives() {
        this.findNavController()
            .navigate(LaunchFragmentDirections.actionLaunchFragmentToRepresentativeFragment())
    }

}
