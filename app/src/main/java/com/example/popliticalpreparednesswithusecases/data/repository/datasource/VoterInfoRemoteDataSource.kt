package com.example.popliticalpreparednesswithusecases.data.repository.datasource

import com.example.popliticalpreparednesswithusecases.data.model.VoterInfoResponse
import retrofit2.Response

interface VoterInfoRemoteDataSource {

    suspend fun getVoterInfo(electionId: String): Response<VoterInfoResponse>

}