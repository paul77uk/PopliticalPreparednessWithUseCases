package com.example.popliticalpreparednesswithusecases

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.popliticalpreparednesswithusecases.data.util.Resource
import com.example.popliticalpreparednesswithusecases.databinding.FragmentElectionBinding
import com.example.popliticalpreparednesswithusecases.presentation.adapters.ElectionAdapter
import com.example.popliticalpreparednesswithusecases.presentation.viewmodel.ElectionViewModel

class ElectionFragment : Fragment() {

    private lateinit var viewModel: ElectionViewModel
    private lateinit var electionAdapter: ElectionAdapter
    private lateinit var savedElectionAdapter: ElectionAdapter
    private lateinit var fragmentElectionBinding: FragmentElectionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_election, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentElectionBinding = FragmentElectionBinding.bind(view)
        viewModel = (activity as MainActivity).viewModel
        electionAdapter = (activity as MainActivity).electionAdapter
        savedElectionAdapter = (activity as MainActivity).savedElectionAdapter
        electionAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("selected_election", it)
            }
            findNavController().navigate(
                R.id.action_electionFragment_to_voterInfoFragment,
                bundle
            )
        }
        electionAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("selected_election", it)
            }
            findNavController().navigate(
                R.id.action_electionFragment_to_voterInfoFragment,
                bundle
            )
        }

        viewModel.getSavedElections().observe(viewLifecycleOwner, {
            savedElectionAdapter.differ.submitList(it)
        })

        initRecycleView()
        viewElectionList()
        initSavedElectionRecycleView()
    }

    private fun viewElectionList() {
        viewModel.getElections()
        viewModel.getSavedElections()
        viewModel.elections.observe(viewLifecycleOwner, { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressbar()
                    response.data?.let {
                        electionAdapter.differ.submitList(it.elections.toList())
                    }
                }
                is Resource.Error -> {
                    hideProgressbar()
                    response.message?.let {
                        Toast.makeText(activity, "An error occurred : $it", Toast.LENGTH_LONG).show()
                    }
                }
                is Resource.Loading -> {
                    showProgressbar()
                }
            }
        })
    }

    private fun initRecycleView() {
//        electionAdapter = ElectionAdapter()
        fragmentElectionBinding.upcomingElectionsRecyclerView.apply {
            adapter = electionAdapter
            layoutManager = LinearLayoutManager(activity)
        }
//        fragmentElectionBinding.upcomingElectionsRecyclerView.adapter = electionAdapter
//        fragmentElectionBinding.upcomingElectionsRecyclerView.layoutManager = LinearLayoutManager(activity)
    }

    private fun initSavedElectionRecycleView() {
        fragmentElectionBinding.savedElectionsRecyclerView.apply {
            adapter = savedElectionAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    private fun showProgressbar() {
        fragmentElectionBinding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressbar() {
        fragmentElectionBinding.progressBar.visibility = View.GONE
    }

}