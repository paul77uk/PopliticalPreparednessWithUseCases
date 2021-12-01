package com.example.popliticalpreparednesswithusecases

import com.example.popliticalpreparednesswithusecases.presentation.adapters.RepresentativeAdapter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.popliticalpreparednesswithusecases.data.util.Resource
import com.example.popliticalpreparednesswithusecases.databinding.RepresentativeFragmentBinding
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

//        val concatAdapter = ConcatAdapter(representativeAdapter, officialAdapter)
        initRecyclerView()
//        initRecyclerView2()
        displayRepresentativesFromFormAddress()
//        setSearchView()
    }

    private fun initRecyclerView() {
        fragmentRepresentativeBinding.representativesRecyclerView.apply {
//            val concatAdapter = ConcatAdapter(representativeAdapter, officialAdapter)
            adapter = representativeAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

//    private fun initRecyclerView2() {
//        fragmentRepresentativeBinding.representativesRecyclerView.apply {
//            adapter = officialAdapter
//            layoutManager = LinearLayoutManager(activity)
//        }
//    }

    fun getRepresentatives() {
//                                        Log.i("TAG4", viewModel.representativeItem.value?.get(0)?.official?.name!!)
        viewModel.representative.observe(viewLifecycleOwner, { response ->
            when (response) {
                is Resource.Success -> {
                                    Log.i("TAG2", response.data?.officials?.get(0)?.name!!)
//                     Log.i("TAG3", viewModel.representativeItem.value?.get(0)?.official?.name!!)
                    hideProgressbar()
                    viewModel.representativeItem.observe(viewLifecycleOwner, {
                                       representativeAdapter.differ.submitList(it)
                        Log.i("TAG", it[0].office.name)


                    })
//                     Log.i("TAG5", response.data.offices[0].name)
//                    response.data?.let {
//                        representativeAdapter.differ.submitList(it)
////                        officialAdapter.differ.submitList(it.officials)
//                    }
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

    fun displayRepresentativesFromFormAddress() {

        fragmentRepresentativeBinding.apply {
            button.setOnClickListener {
                val address =
                    "${addressLine1EditText.text} ${addressLine2EditText.text} ${cityEditText.text} ${stateEditText.text} ${zipEditText.text}"
                viewModel.getRepresentatives(address)
                getRepresentatives()
            }
        }
    }

}