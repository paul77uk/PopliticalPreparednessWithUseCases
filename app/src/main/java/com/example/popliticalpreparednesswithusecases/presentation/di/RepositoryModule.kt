package com.example.popliticalpreparednesswithusecases.presentation.di

import com.example.popliticalpreparednesswithusecases.data.repository.ElectionRepositoryImpl
import com.example.popliticalpreparednesswithusecases.data.repository.RepresentativeRepositoryImpl
import com.example.popliticalpreparednesswithusecases.data.repository.datasource.ElectionLocalDataSource
import com.example.popliticalpreparednesswithusecases.data.repository.datasource.ElectionRemoteDataSource
import com.example.popliticalpreparednesswithusecases.data.repository.datasource.RepresentativeRemoteDataSource
import com.example.popliticalpreparednesswithusecases.domain.repository.ElectionsRepository
import com.example.popliticalpreparednesswithusecases.domain.repository.RepresentativeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideElectionRepository(
        electionRemoteDataSource: ElectionRemoteDataSource,
        electionLocalDataSource: ElectionLocalDataSource
    ): ElectionsRepository {
        return ElectionRepositoryImpl(electionRemoteDataSource, electionLocalDataSource)
    }

    @Singleton
    @Provides
    fun provideRepresentativeRepository(
        representativeRemoteDataSource: RepresentativeRemoteDataSource
    ): RepresentativeRepository {
        return RepresentativeRepositoryImpl(representativeRemoteDataSource)
    }

}