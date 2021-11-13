package com.example.popliticalpreparednesswithusecases.presentation.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.popliticalpreparednesswithusecases.data.model.ElectionResponse
import com.example.popliticalpreparednesswithusecases.data.util.Resource
import com.example.popliticalpreparednesswithusecases.domain.usecase.GetUpcomingElectionsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ElectionViewModel(
    private val app: Application,
    private val getUpcomingElectionsUseCase: GetUpcomingElectionsUseCase
) : AndroidViewModel(app) {

    val elections: MutableLiveData<Resource<ElectionResponse>> = MutableLiveData()

    fun getElections() = viewModelScope.launch(Dispatchers.IO) {
        elections.postValue(Resource.Loading())
        try {
            if (isNetworkAvailable(app)) {
                val apiResult = getUpcomingElectionsUseCase.execute()
                elections.postValue(apiResult)
            } else {
                elections.postValue(Resource.Error("Internet is not available"))
            }
        } catch (e: Exception) {
            elections.postValue(Resource.Error(e.message.toString()))
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