package com.example.popliticalpreparednesswithusecases

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.navArgs
import com.example.popliticalpreparednesswithusecases.data.model.Election
import com.example.popliticalpreparednesswithusecases.data.util.Resource
import com.example.popliticalpreparednesswithusecases.databinding.FragmentVoterInfoBinding
import com.example.popliticalpreparednesswithusecases.presentation.viewmodel.ElectionViewModel
import com.example.popliticalpreparednesswithusecases.presentation.viewmodel.VoterInfoViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class VoterInfoFragment : Fragment() {

    private lateinit var fragmentVoterInfoBinding: FragmentVoterInfoBinding
    private lateinit var viewModel: VoterInfoViewModel
//    private lateinit var electionViewModel: ElectionViewModel

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
        viewModel = (activity as MainActivity).voterInfoViewModel
//        electionViewModel = (activity as MainActivity).viewModel
        val args: VoterInfoFragmentArgs by this.navArgs()
        val election = args.selectedElection

//        viewModel = (activity as MainActivity).viewModel

        fragmentVoterInfoBinding.apply {
            if (election.name != "") electionName.text =
                election.name
            electionDay.text =
                election.electionDay ?: ""
            Log.i("TAG", "${election.id}")

            Log.i("TAG5", election.ocdDivisionId.substringAfter("state:","la"))

            votingLocations.setOnClickListener {
                Log.i("TAG5", election.ocdDivisionId.substringAfter("state:","la"))
                viewModel.getVoterInfo(election.id.toString())
                getVoterInfo()
                Log.i("Tag3", "${viewModel.voterInfo.value}")

//                viewModel.voterInfo.observe(viewLifecycleOwner, Observer {
//                    Log.i("TAG4", "${it.data?.state?.get(0)?.electionAdministrationBody?.electionInfoUrl}")
//                })

//                val openURL = Intent(Intent.ACTION_VIEW)
//                openURL.data =
//                    Uri.parse("https://www.tutorialkart.com/")
//                startActivity(openURL)
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

    fun getVoterInfo() {

        viewModel.voterInfo.observe(viewLifecycleOwner, { response ->
            when (response) {
                is Resource.Success -> {
//                    hideProgressbar()
                    response.data?.let {
                        val openURL = Intent(Intent.ACTION_VIEW)
                        openURL.data =
                            Uri.parse(it.state[0].electionAdministrationBody.electionInfoUrl)
                        startActivity(openURL)

//                        representativeAdapter.differ.submitList(it.offices)
                    }
                }
                is Resource.Error -> {
//                    hideProgressbar()
                    response.message?.let {
                        Toast.makeText(activity, "An error occurred : $it", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
                is Resource.Loading -> {
//                    response.data?.let {
//                        fragmentRepresentativeBinding.textView.text = it.offices[0].name
////                        electionAdapter.differ.submitList(it.elections.toList())
//                    }
//                    showProgressbar()
                }
            }
        })
    }


    }