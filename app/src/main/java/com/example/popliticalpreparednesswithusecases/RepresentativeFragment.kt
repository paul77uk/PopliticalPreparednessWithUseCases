package com.example.popliticalpreparednesswithusecases

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.popliticalpreparednesswithusecases.data.model.Address
import com.example.popliticalpreparednesswithusecases.data.util.Resource
import com.example.popliticalpreparednesswithusecases.databinding.RepresentativeFragmentBinding
import com.example.popliticalpreparednesswithusecases.presentation.adapters.RepresentativeAdapter
import com.example.popliticalpreparednesswithusecases.presentation.viewmodel.RepresentativeViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import java.util.*

class RepresentativeFragment : Fragment() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var viewModel: RepresentativeViewModel
    private lateinit var representativeAdapter: RepresentativeAdapter

    private lateinit var fragmentRepresentativeBinding: RepresentativeFragmentBinding

    private val REQUEST_LOCATION_PERMISSION = 1

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
        fragmentRepresentativeBinding.apply {
            findRepresentativesButton.setOnClickListener {
                displayRepresentativesFromFormAddress()
                hideKeyboard()
            }
            useMyLocationButton.setOnClickListener {
                getLocation()
            }
        }
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
//                    Log.i("TAG2", response.data?.officials?.get(0)?.name!!)
//                     Log.i("TAG3", viewModel.representativeItem.value?.get(0)?.official?.name!!)
                    hideProgressbar()
//                    viewModel.representativeString.value?.let { Log.i("TAG5", it[1]) }
//                        for (i in 0..(response.data.offices.size)) {
//                            viewModel.representativeItem.value?.get(i)?.office = response.data.offices[i]
//                        }
//                        for (i in 0..(response.data.officials.size)) {
//                            viewModel.representativeItem.value?.get(i)?.official = response.data.officials[i]
//                        }
                    viewModel.representativeItem.observe(viewLifecycleOwner, {
                        representativeAdapter.differ.submitList(it)
                    })

//                    if (viewModel.representativeItem.value != null) {
//                        Log.i("TAG", viewModel.representativeItem.value?.get(0)?.office?.name!!)
//                        Log.i("TAG3", response.data.officials[0].name)
//                    } else Log.i("TAG4", "null")


//                     Log.i("TAG5", response.data.offices[0].name)
//                    response.data?.let {
//                        representativeAdapter.differ.submitList(it)
////                        officialAdapter.differ.submitList(it.officials)
//                    }
                }
                is Resource.Error -> {
                    hideProgressbar()
                    response.message?.let {
                        Toast.makeText(activity, "Address needs to be a valid US address", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
                is Resource.Loading -> {
//                    response.data?.let {
//                        fragmentRepresentativeBinding.textView.text = it.offices[0].name
////                        electionAdapter.differ.submitList(it.elections.toList())
//                    }
                    showProgressbar()
                }
            }
        })
    }

    private fun showProgressbar() {
        fragmentRepresentativeBinding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressbar() {
        fragmentRepresentativeBinding.progressBar.visibility = View.GONE
    }

    fun displayRepresentativesFromFormAddress() {

        fragmentRepresentativeBinding.apply {

            val address =
                "${addressLine1EditText.text} ${addressLine2EditText.text} ${cityEditText.text} ${stateEditText.text} ${zipEditText.text}"
            viewModel.getRepresentatives(address)
            getRepresentatives()
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        //TODO: Handle location permission result to get location on permission granted
        if (requestCode == REQUEST_LOCATION_PERMISSION) {
            if (grantResults.contains(PackageManager.PERMISSION_GRANTED)) {
//                getLocation()
            }
        }
    }

    private fun checkLocationPermissions(): Boolean {
        return if (isPermissionGranted()) {
            true
        } else {
            //TODO: Request Location permissions
            requestPermissions(
                arrayOf<String>(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_LOCATION_PERMISSION
            )
            false
        }
    }

    private fun isPermissionGranted(): Boolean {
        //TODO: Check if permission is already granted and return (true = granted, false = denied/other)
        return ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun getLocation() {
        //TODO: Get location from LocationServices
        if (checkLocationPermissions()) {
            LocationServices.getFusedLocationProviderClient(requireContext())
                .lastLocation.addOnSuccessListener { locationResult ->

//                    locationResult.setLatitude(38.8860);
//                    locationResult.setLongitude(-76.9995);
//                    locationResult.setLatitude(locationResult.latitude)
//                    locationResult.setLongitude(locationResult.longitude)
                    val location = geoCodeLocation(locationResult)
                    fragmentRepresentativeBinding.apply {

                        addressLine1EditText.setText(location.line1)
                        addressLine2EditText.setText(location.line2)
                        cityEditText.setText(location.city)
                        stateEditText.setText(location.state)
                        zipEditText.setText(location.zip)

                    }
                    displayRepresentativesFromFormAddress()

                }
        }
        //TODO: The geoCodeLocation method is a helper function to change the lat/long location to a human readable street address
    }

    private fun geoCodeLocation(location: Location): Address {
        val geocoder = Geocoder(context, Locale.getDefault())
        return geocoder.getFromLocation(location.latitude, location.longitude, 1)
            .map { address ->
                Address(
                    address.thoroughfare,
                    address.subThoroughfare,
                    address.locality,
                    address.adminArea,
                    address.postalCode
                )
            }
            .first()
    }

    private fun hideKeyboard() {
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(requireView().windowToken, 0)
    }


}