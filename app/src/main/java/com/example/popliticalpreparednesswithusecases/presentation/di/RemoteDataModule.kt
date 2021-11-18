package com.example.popliticalpreparednesswithusecases.presentation.di

import com.example.popliticalpreparednesswithusecases.data.api.APIService
import com.example.popliticalpreparednesswithusecases.data.repository.datasource.ElectionRemoteDataSource
import com.example.popliticalpreparednesswithusecases.data.repository.datasource.RepresentativeRemoteDataSource
import com.example.popliticalpreparednesswithusecases.data.repository.datasourceimpl.ElectionRemoteDataSourceImpl
import com.example.popliticalpreparednesswithusecases.data.repository.datasourceimpl.RepresentativeRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RemoteDataModule {

    @Singleton
    @Provides
    fun provideElectionRemoteDataSource(apiService: APIService): ElectionRemoteDataSource {
        return ElectionRemoteDataSourceImpl(apiService)
    }

    @Singleton
    @Provides
    fun provideRepresentativeRemoteDataSource(apiService: APIService): RepresentativeRemoteDataSource {
        return RepresentativeRemoteDataSourceImpl(apiService)
    }

}