package com.example.popliticalpreparednesswithusecases

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.popliticalpreparednesswithusecases.databinding.FragmentVoterInfoBinding
import com.example.popliticalpreparednesswithusecases.presentation.viewmodel.ElectionViewModel
import com.google.android.material.snackbar.Snackbar

class VoterInfoFragment : Fragment() {

    private lateinit var fragmentVoterInfoBinding: FragmentVoterInfoBinding
    private lateinit var viewModel: ElectionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_voter_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentVoterInfoBinding = FragmentVoterInfoBinding.bind(view)
        val args: VoterInfoFragmentArgs by this.navArgs()
        val election = args.selectedElection

        viewModel = (activity as MainActivity).viewModel

        fragmentVoterInfoBinding.apply {
            if (election.name != "") electionName.text =
                election.name
            electionDay.text =
                election.electionDay ?: ""

            votingLocations.setOnClickListener {
//                viewModel.getVoterInfo(election.id.toString())
                val openURL = Intent(Intent.ACTION_VIEW)
                openURL.data = Uri.parse("https://www.tutorialkart.com/")
                startActivity(openURL)
            }

            ballotInfo.setOnClickListener {

            }

            followElectionButton.setOnClickListener {
                viewModel.saveElection(election)
                Snackbar.make(view, "Saved Successfully!", Snackbar.LENGTH_SHORT).show()
            }

            unfollowElectionButton.setOnClickListener {
                viewModel.deleteElections(election)
                Snackbar.make(view, "deleted Successfully", Snackbar.LENGTH_SHORT)
                    .apply {
                        setAction("Undo") {
                            viewModel.saveElection(election)
                        }
                            .show()
                    }
            }
        }

    }

}