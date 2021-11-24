package com.example.popliticalpreparednesswithusecases.presentation.di

import com.example.popliticalpreparednesswithusecases.presentation.adapters.ElectionAdapter
import com.example.popliticalpreparednesswithusecases.presentation.adapters.RepresentativeListAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AdapterModule {

    @Singleton
    @Provides
    fun provideElectionAdapter(): ElectionAdapter {
        return ElectionAdapter()
    }

    @Singleton
    @Provides
    fun provideRepresentativeAdapter(): RepresentativeListAdapter {
        return RepresentativeListAdapter()
    }

}