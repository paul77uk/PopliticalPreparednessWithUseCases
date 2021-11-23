package com.example.popliticalpreparednesswithusecases.presentation.di

import com.example.popliticalpreparednesswithusecases.data.db.ElectionDAO
import com.example.popliticalpreparednesswithusecases.data.repository.datasource.ElectionLocalDataSource
import com.example.popliticalpreparednesswithusecases.data.repository.datasourceimpl.ElectionLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDataModule {

    @Singleton
    @Provides
    fun provideLocalDataSource(electionDAO: ElectionDAO): ElectionLocalDataSource {
        return ElectionLocalDataSourceImpl(electionDAO)
    }

}