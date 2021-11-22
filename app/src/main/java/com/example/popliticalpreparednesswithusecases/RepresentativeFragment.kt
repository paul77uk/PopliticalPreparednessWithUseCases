package com.example.popliticalpreparednesswithusecases

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.popliticalpreparednesswithusecases.data.util.Resource
import com.example.popliticalpreparednesswithusecases.databinding.RepresentativeFragmentBinding
import com.example.popliticalpreparednesswithusecases.presentation.adapters.RepresentativeAdapter
import com.example.popliticalpreparednesswithusecases.presentation.viewmodel.RepresentativeViewModel

class RepresentativeFragment : Fragment() {

    private lateinit var viewModel: RepresentativeViewModel
    private lateinit var representativeAdapter: RepresentativeAdapter

    private lateinit var fragmentRepresentativeBinding: RepresentativeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.representative_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentRepresentativeBinding = RepresentativeFragmentBinding.bind(view)
        viewModel = (activity as MainActivity).representativeViewModel
        representativeAdapter = (activity as MainActivity).representativeAdapter

        initRecyclerView()
        displayRepresentativesFromFormAddress()
//        setSearchView()
    }

    private fun initRecyclerView() {
        fragmentRepresentativeBinding.representativesRecyclerView.apply {
            adapter = representativeAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    fun getRepresentatives() {

        viewModel.representative.observe(viewLifecycleOwner, { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressbar()
                    response.data?.let {
                        representativeAdapter.differ.submitList(it.representatives.toList())
//                        electionAdapter.differ.submitList(it.elections.toList())
                    }
                }
                is Resource.Error -> {
                    hideProgressbar()
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

    private fun showProgressbar() {
//        fragmentElectionBinding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressbar() {
//        fragmentElectionBinding.progressBar.visibility = View.GONE
    }
//
//    private fun setSearchView() {
//        fragmentRepresentativeBinding.searchView.setOnQueryTextListener(object :
//            SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(p0: String?): Boolean {
//                viewModel.getRepresentatives(p0.toString())
//                getRepresentatives()
//                return false
//            }
//
//            override fun onQueryTextChange(p0: String?): Boolean {
//                MainScope().launch {
//                    delay(2000)
//                    viewModel.getRepresentatives(p0.toString())
//                    getRepresentatives()
//                }
//                return false
//            }
//
//        })

//        fragmentRepresentativeBinding.searchView.setOnCloseListener(
//            object : SearchView.OnCloseListener {
//                override fun onClose(): Boolean {
//                    getRepresentatives()
//                    return false
//                }
//            }
//        )
//    }

    fun displayRepresentativesFromFormAddress() {

        fragmentRepresentativeBinding.apply {
            button.setOnClickListener {
//                val addressLine2 =
//                    if (!addressLine2EditText.text.isNullOrEmpty()) " ${addressLine2EditText.text}" else ""
//                val city =
//                    if (!cityEditText.text.isNullOrEmpty()) " ${cityEditText.text}" else ""
//                val state =
//                    if (!stateEditText.text.isNullOrEmpty()) " ${stateEditText.text}" else ""
//                val zip =
//                    if (!zipEditText.text.isNullOrEmpty()) " ${zipEditText.text}" else ""
                val address =
                    "${addressLine1EditText.text} ${addressLine2EditText.text} ${cityEditText.text} ${stateEditText.text} ${zipEditText.text}"
                viewModel.getRepresentatives(address)
                getRepresentatives()
            }
        }
    }

}