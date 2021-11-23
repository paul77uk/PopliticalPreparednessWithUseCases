package com.example.popliticalpreparednesswithusecases.data.repository

import com.example.popliticalpreparednesswithusecases.data.model.Election
import com.example.popliticalpreparednesswithusecases.data.model.ElectionResponse
import com.example.popliticalpreparednesswithusecases.data.repository.datasource.ElectionLocalDataSource
import com.example.popliticalpreparednesswithusecases.data.repository.datasource.ElectionRemoteDataSource
import com.example.popliticalpreparednesswithusecases.data.util.Resource
import com.example.popliticalpreparednesswithusecases.domain.repository.ElectionsRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class ElectionRepositoryImpl(
    private val electionRemoteDataSource: ElectionRemoteDataSource,
    private val electionLocalDataSource: ElectionLocalDataSource
): ElectionsRepository {

    override suspend fun getUpcomingElections(): Resource<ElectionResponse> {
        return responseToResource(electionRemoteDataSource.getElections())
    }

    private fun responseToResource(response: Response<ElectionResponse>):Resource<ElectionResponse>{
        if (response.isSuccessful) {
            response.body()?.let { result->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }

    override suspend fun saveElection(election: Election) {
        electionLocalDataSource.saveElectionToDB(election)
    }

    override suspend fun unfollowElection(election: Election) {
        electionLocalDataSource.deleteElectionsFromDB(election)
    }

    override fun getSavedElection(): Flow<List<Election>> {
        return electionLocalDataSource.getSavedElections()
    }

}