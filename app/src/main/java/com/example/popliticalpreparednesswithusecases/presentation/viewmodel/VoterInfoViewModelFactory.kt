package com.example.popliticalpreparednesswithusecases.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.popliticalpreparednesswithusecases.domain.usecase.GetSavedElectionUseCase
import com.example.popliticalpreparednesswithusecases.domain.usecase.GetVoterInfoUseCase
import com.example.popliticalpreparednesswithusecases.domain.usecase.SaveElectionUseCase
import com.example.popliticalpreparednesswithusecases.domain.usecase.UnfollowElectionUseCase

class VoterInfoViewModelFactory(
    private val app: Application,
    private val getVoterInfoUseCase: GetVoterInfoUseCase,
    private val saveElectionUseCase: SaveElectionUseCase,
    private val unfollowElectionUseCase: UnfollowElectionUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return VoterInfoViewModel(
            app,
            getVoterInfoUseCase,
            saveElectionUseCase,
            unfollowElectionUseCase
        ) as T
    }

}