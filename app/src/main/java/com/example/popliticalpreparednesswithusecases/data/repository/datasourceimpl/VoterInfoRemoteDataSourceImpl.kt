package com.example.popliticalpreparednesswithusecases.data.repository.datasourceimpl

import com.example.popliticalpreparednesswithusecases.data.api.APIService
import com.example.popliticalpreparednesswithusecases.data.model.RepresentativeResponse
import com.example.popliticalpreparednesswithusecases.data.model.VoterInfoResponse
import com.example.popliticalpreparednesswithusecases.data.repository.datasource.RepresentativeRemoteDataSource
import com.example.popliticalpreparednesswithusecases.data.repository.datasource.VoterInfoRemoteDataSource
import retrofit2.Response

class VoterInfoRemoteDataSourceImpl(private val apiService: APIService): VoterInfoRemoteDataSource {

    override suspend fun getVoterInfo(electionId: String): Response<VoterInfoResponse> {
        return apiService.getVoterInfo(electionId = electionId)
    }

}