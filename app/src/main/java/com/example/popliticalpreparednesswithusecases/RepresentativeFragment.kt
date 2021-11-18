package com.example.popliticalpreparednesswithusecases

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.popliticalpreparednesswithusecases.data.util.Resource
import com.example.popliticalpreparednesswithusecases.databinding.RepresentativeFragmentBinding
import com.example.popliticalpreparednesswithusecases.presentation.viewmodel.RepresentativeViewModel
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class RepresentativeFragment : Fragment() {

    private lateinit var viewModel: RepresentativeViewModel

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
        displayRepresentativesFromFormAddress()
//        setSearchView()
    }

    fun getRepresentatives() {

        viewModel.representative.observe(viewLifecycleOwner, { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressbar()
                    response.data?.let {
                        fragmentRepresentativeBinding.textView.text = it.officials[3].name
//                        electionAdapter.differ.submitList(it.elections.toList())
                    }
                }
                is Resource.Error -> {
                    hideProgressbar()
                    fragmentRepresentativeBinding.textView.text = ""
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

    private fun setSearchView() {
        fragmentRepresentativeBinding.searchView.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                viewModel.getRepresentatives(p0.toString())
                getRepresentatives()
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                MainScope().launch {
                    delay(2000)
                    viewModel.getRepresentatives(p0.toString())
                    getRepresentatives()
                }
                return false
            }

        })

        fragmentRepresentativeBinding.searchView.setOnCloseListener(
            object : SearchView.OnCloseListener {
                override fun onClose(): Boolean {
                    getRepresentatives()
                    return false
                }
            }
        )
    }

    fun displayRepresentativesFromFormAddress() {
        fragmentRepresentativeBinding.button.setOnClickListener {
            val address = fragmentRepresentativeBinding.editTextTextPersonName.text.toString()
            viewModel.getRepresentatives(address)
            getRepresentatives()
        }
    }

}