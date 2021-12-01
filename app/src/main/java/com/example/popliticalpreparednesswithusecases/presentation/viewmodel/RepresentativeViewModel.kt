package com.example.popliticalpreparednesswithusecases.presentation.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.popliticalpreparednesswithusecases.data.model.Representative
import com.example.popliticalpreparednesswithusecases.data.model.RepresentativeResponse
import com.example.popliticalpreparednesswithusecases.data.util.Resource
import com.example.popliticalpreparednesswithusecases.domain.usecase.GetSearchedRepresentativeUseCase
import kotlinx.coroutines.launch

class RepresentativeViewModel(
    private val app: Application,
    private val getSearchedRepresentativeUseCase: GetSearchedRepresentativeUseCase
) : AndroidViewModel(app) {

    // TODO: Implement the ViewModel
    val representative: MutableLiveData<Resource<RepresentativeResponse>> = MutableLiveData()
    val representativeItem: MutableLiveData<List<Representative>> = MutableLiveData()

    fun getRepresentatives(
        address: String
    ) = viewModelScope.launch {
        representative.postValue(Resource.Loading())
        try {
            if (isNetworkAvailable(app)) {
                val response = getSearchedRepresentativeUseCase.execute(address)
                representative.postValue(response)
                for (i in 0..(response.data?.offices?.size!!)) {
                    representativeItem.value?.get(i)?.office  = response.data.offices[i]
                }
                for (i in 0..(response.data.officials.size)) {
                    representativeItem.value?.get(i)?.official  = response.data.officials[i]
                }
            } else {
                representative.postValue(Resource.Error("No internet connection"))
            }
        } catch (e: Exception) {
            representative.postValue(Resource.Error(e.message.toString()))
        }
    }

    private fun isNetworkAvailable(context: Context?): Boolean {
        if (context == null) return false
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        return true
                    }
                }
            }
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                return true
            }
        }
        return false
    }

}