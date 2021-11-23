package com.example.popliticalpreparednesswithusecases.presentation.di

import android.app.Application
import androidx.room.Room
import com.example.popliticalpreparednesswithusecases.data.db.ElectionDAO
import com.example.popliticalpreparednesswithusecases.data.db.ElectionDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideElectionDatabase(app: Application): ElectionDatabase {
        return Room.databaseBuilder(app, ElectionDatabase::class.java, "election_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideElectionDao(electionDatabase: ElectionDatabase): ElectionDAO {
        return electionDatabase.getElectionsDAO()
    }

}