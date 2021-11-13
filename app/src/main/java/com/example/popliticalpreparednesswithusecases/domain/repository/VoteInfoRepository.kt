package com.example.popliticalpreparednesswithusecases.domain.repository

import com.example.popliticalpreparednesswithusecases.data.model.VoterInfoResponse
import com.example.popliticalpreparednesswithusecases.data.util.Resource

interface VoteInfoRepository {

    suspend fun getVoterInfo(): Resource<VoterInfoResponse>

}