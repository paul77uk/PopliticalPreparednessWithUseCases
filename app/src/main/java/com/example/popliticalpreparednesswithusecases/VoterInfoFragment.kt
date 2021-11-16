package com.example.popliticalpreparednesswithusecases

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.popliticalpreparednesswithusecases.databinding.FragmentVoterInfoBinding

class VoterInfoFragment : Fragment() {

    private lateinit var fragmentVoterInfoBinding: FragmentVoterInfoBinding

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
        val args: VoterInfoFragmentArgs by navArgs()
        val election = args.selectedElection
        if (election.electionDay != "") fragmentVoterInfoBinding.infoFragmentTextView.text =
            election.electionDay
    }

}