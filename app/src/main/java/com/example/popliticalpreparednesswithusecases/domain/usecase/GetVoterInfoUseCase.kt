package com.example.popliticalpreparednesswithusecases.domain.usecase

import com.example.popliticalpreparednesswithusecases.data.model.VoterInfoResponse
import com.example.popliticalpreparednesswithusecases.data.util.Resource
import com.example.popliticalpreparednesswithusecases.domain.repository.VoteInfoRepository

class GetVoterInfoUseCase(private val voterInfoRepository: VoteInfoRepository) {

    suspend fun execute(): Resource<VoterInfoResponse> {
        return voterInfoRepository.getVoterInfo()
    }

}