package com.example.popliticalpreparednesswithusecases.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.popliticalpreparednesswithusecases.domain.usecase.GetUpcomingElectionsUseCase

class ElectionViewModelFactory(
    private val app: Application,
    private val getUpcomingElectionsUseCase: GetUpcomingElectionsUseCase
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ElectionViewModel(
            app,
            getUpcomingElectionsUseCase
        ) as T
    }

}