package com.example.popliticalpreparednesswithusecases.presentation.di

import com.example.popliticalpreparednesswithusecases.domain.repository.ElectionsRepository
import com.example.popliticalpreparednesswithusecases.domain.repository.RepresentativeRepository
import com.example.popliticalpreparednesswithusecases.domain.usecase.*
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

    @Singleton
    @Provides
    fun provideSaveElectionUseCase(
        electionsRepository: ElectionsRepository
    ) : SaveElectionUseCase {
        return SaveElectionUseCase(electionsRepository)
    }

    @Singleton
    @Provides
    fun provideGetSavedElectionUseCase(
        electionsRepository: ElectionsRepository
    ) : GetSavedElectionUseCase {
        return GetSavedElectionUseCase(electionsRepository)
    }

    @Singleton
    @Provides
    fun provideDeleteSavedElectionUseCase(
        electionsRepository: ElectionsRepository
    ) : UnfollowElectionUseCase {
        return UnfollowElectionUseCase(electionsRepository)
    }

}