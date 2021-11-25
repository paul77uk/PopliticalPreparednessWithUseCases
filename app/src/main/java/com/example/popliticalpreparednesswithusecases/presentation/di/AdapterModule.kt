package com.example.popliticalpreparednesswithusecases.presentation.di

import com.example.popliticalpreparednesswithusecases.presentation.adapters.ElectionAdapter
import com.example.popliticalpreparednesswithusecases.presentation.adapters.RepresentativeAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AdapterModule {

    @Provides
    fun provideElectionAdapter(): ElectionAdapter {
        return ElectionAdapter()
    }

    @Singleton
    @Provides
    fun provideRepresentativeAdapter(): RepresentativeAdapter {
        return RepresentativeAdapter()
    }

}