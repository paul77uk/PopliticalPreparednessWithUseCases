package com.example.popliticalpreparednesswithusecases.presentation.di

import android.app.Application
import com.example.popliticalpreparednesswithusecases.domain.usecase.GetSearchedRepresentativeUseCase
import com.example.popliticalpreparednesswithusecases.domain.usecase.GetUpcomingElectionsUseCase
import com.example.popliticalpreparednesswithusecases.presentation.viewmodel.ElectionViewModelFactory
import com.example.popliticalpreparednesswithusecases.presentation.viewmodel.RepresentativeViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FactoryModule {

    @Singleton
    @Provides
    fun provideElectionViewModelFactory(
        application: Application,
        getUpcomingElectionsUseCase: GetUpcomingElectionsUseCase
    ): ElectionViewModelFactory {
        return ElectionViewModelFactory(
            application,
            getUpcomingElectionsUseCase
        )
    }

    @Singleton
    @Provides
    fun provideRepresentativeViewModelFactory(
        application: Application,
        getSearchedRepresentativeUseCase: GetSearchedRepresentativeUseCase
    ): RepresentativeViewModelFactory {
        return RepresentativeViewModelFactory(
            application,
            getSearchedRepresentativeUseCase
        )
    }

}