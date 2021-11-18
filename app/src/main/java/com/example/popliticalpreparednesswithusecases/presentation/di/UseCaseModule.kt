package com.example.popliticalpreparednesswithusecases.presentation.di

import com.example.popliticalpreparednesswithusecases.domain.repository.ElectionsRepository
import com.example.popliticalpreparednesswithusecases.domain.repository.RepresentativeRepository
import com.example.popliticalpreparednesswithusecases.domain.usecase.GetSearchedRepresentativeUseCase
import com.example.popliticalpreparednesswithusecases.domain.usecase.GetUpcomingElectionsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Singleton
    @Provides
    fun provideElectionUseCase(
        electionsRepository: ElectionsRepository
    ) : GetUpcomingElectionsUseCase {
        return GetUpcomingElectionsUseCase(electionsRepository)
    }

    @Singleton
    @Provides
    fun provideRepresentativeUseCase(
        representativeRepository: RepresentativeRepository
    ) : GetSearchedRepresentativeUseCase {
        return GetSearchedRepresentativeUseCase(representativeRepository)
    }

}