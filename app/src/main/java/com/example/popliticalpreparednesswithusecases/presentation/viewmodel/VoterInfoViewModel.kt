package com.example.popliticalpreparednesswithusecases.presentation.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.popliticalpreparednesswithusecases.data.model.Election
import com.example.popliticalpreparednesswithusecases.data.model.VoterInfoResponse
import com.example.popliticalpreparednesswithusecases.data.util.Resource
import com.example.popliticalpreparednesswithusecases.domain.usecase.GetVoterInfoUseCase
import com.example.popliticalpreparednesswithusecases.domain.usecase.SaveElectionUseCase
import com.example.popliticalpreparednesswithusecases.domain.usecase.UnfollowElectionUseCase
import kotlinx.coroutines.launch

class VoterInfoViewModel(
    private val app: Application,
    private val getVoterInfoUseCase: GetVoterInfoUseCase,
    private val saveElectionUseCase: SaveElectionUseCase,
    private val unfollowElectionUseCase: UnfollowElectionUseCase,
) : AndroidViewModel(app) {

    val voterInfo: MutableLiveData<Resource<VoterInfoResponse>> = MutableLiveData()

    fun getVoterInfo(
        electionId: String,
        address: String
    ) = viewModelScope.launch {
        voterInfo.value = Resource.Loading()
        try {
            if (isNetworkAvailable(app)) {
                val response = getVoterInfoUseCase.execute(electionId, address)
                voterInfo.value = response
            } else {
                voterInfo.value = Resource.Error("No internet connection")
            }
        } catch (e: Exception) {
            voterInfo.value = Resource.Error(e.message.toString())
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

    // local data
    fun saveElection(election: Election) = viewModelScope.launch {
        saveElectionUseCase.execute(election)
    }

    fun deleteElections(election: Election) = viewModelScope.launch {
        unfollowElectionUseCase.execute(election)
    }

}