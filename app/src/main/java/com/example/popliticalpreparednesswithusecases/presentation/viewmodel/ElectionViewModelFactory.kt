package com.example.popliticalpreparednesswithusecases.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.popliticalpreparednesswithusecases.domain.usecase.GetSavedElectionUseCase
import com.example.popliticalpreparednesswithusecases.domain.usecase.GetUpcomingElectionsUseCase
import com.example.popliticalpreparednesswithusecases.domain.usecase.SaveElectionUseCase
import com.example.popliticalpreparednesswithusecases.domain.usecase.UnfollowElectionUseCase

class ElectionViewModelFactory(
    private val app: Application,
    private val getUpcomingElectionsUseCase: GetUpcomingElectionsUseCase,
    private val saveElectionUseCase: SaveElectionUseCase,
    private val getSavedElectionUseCase: GetSavedElectionUseCase,
    private val unfollowElectionUseCase: UnfollowElectionUseCase
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ElectionViewModel(
            app,
            getUpcomingElectionsUseCase,
            saveElectionUseCase,
            getSavedElectionUseCase,
            unfollowElectionUseCase
        ) as T
    }

}