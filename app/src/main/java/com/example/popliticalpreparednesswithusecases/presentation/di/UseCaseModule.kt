package com.example.popliticalpreparednesswithusecases.presentation.di

import com.example.popliticalpreparednesswithusecases.domain.repository.ElectionsRepository
import com.example.popliticalpreparednesswithusecases.domain.usecase.GetUpcomingElectionsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    fun provideElectionUseCase(
        electionsRepository: ElectionsRepository
    ) : GetUpcomingElectionsUseCase {
        return GetUpcomingElectionsUseCase(electionsRepository)
    }

}