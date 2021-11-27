package com.example.popliticalpreparednesswithusecases.data.repository

import com.example.popliticalpreparednesswithusecases.data.model.VoterInfoResponse
import com.example.popliticalpreparednesswithusecases.data.repository.datasource.VoterInfoRemoteDataSource
import com.example.popliticalpreparednesswithusecases.data.util.Resource
import com.example.popliticalpreparednesswithusecases.domain.repository.VoterInfoRepository
import retrofit2.Response

class VoterInfoRepositoryImpl(private val voterInfoRemoteDataSource: VoterInfoRemoteDataSource) :
    VoterInfoRepository {

    private fun responseToResource(response: Response<VoterInfoResponse>): Resource<VoterInfoResponse> {
        if (response.isSuccessful) {
            response.body()?.let { result ->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }

    override suspend fun getVoterInfo(electionId: String): Resource<VoterInfoResponse> {
        return responseToResource(voterInfoRemoteDataSource.getVoterInfo(electionId))
    }

}