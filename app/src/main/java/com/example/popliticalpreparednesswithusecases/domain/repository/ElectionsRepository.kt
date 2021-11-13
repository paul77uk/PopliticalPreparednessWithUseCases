package com.example.popliticalpreparednesswithusecases.domain.repository

import com.example.popliticalpreparednesswithusecases.data.model.Election
import com.example.popliticalpreparednesswithusecases.data.model.ElectionResponse
import com.example.popliticalpreparednesswithusecases.data.util.Resource
import kotlinx.coroutines.flow.Flow

interface ElectionsRepository {

    suspend fun getUpcomingElections(): Resource<ElectionResponse>
    suspend fun saveElection(election: Election)
    suspend fun unfollowElection(election: Election)
    fun getSavedElection(): Flow<List<Election>>

}