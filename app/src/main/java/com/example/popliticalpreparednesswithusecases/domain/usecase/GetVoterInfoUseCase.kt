package com.example.popliticalpreparednesswithusecases.domain.usecase

import com.example.popliticalpreparednesswithusecases.data.model.VoterInfoResponse
import com.example.popliticalpreparednesswithusecases.data.util.Resource
import com.example.popliticalpreparednesswithusecases.domain.repository.VoterInfoRepository

class GetVoterInfoUseCase(private val voterInfoRepository: VoterInfoRepository) {

    suspend fun execute(electionId: String): Resource<VoterInfoResponse> {
        return voterInfoRepository.getVoterInfo(electionId)
    }

}