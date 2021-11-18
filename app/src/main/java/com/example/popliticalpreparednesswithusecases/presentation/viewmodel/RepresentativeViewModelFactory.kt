package com.example.popliticalpreparednesswithusecases.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.popliticalpreparednesswithusecases.domain.usecase.GetSearchedRepresentativeUseCase

class RepresentativeViewModelFactory(
    private val app: Application,
    private val getSearchedRepresentativeUseCase: GetSearchedRepresentativeUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RepresentativeViewModel(
            app,
            getSearchedRepresentativeUseCase
        ) as T
    }

}