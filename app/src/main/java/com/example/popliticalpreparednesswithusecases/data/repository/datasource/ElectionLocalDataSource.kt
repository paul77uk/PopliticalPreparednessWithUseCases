package com.example.popliticalpreparednesswithusecases.data.repository.datasource

import com.example.popliticalpreparednesswithusecases.data.model.Election
import kotlinx.coroutines.flow.Flow

interface ElectionLocalDataSource {

    suspend fun saveElectionToDB(election: Election)

    fun getSavedElections(): Flow<List<Election>>

    suspend fun deleteElectionsFromDB(election: Election)

}