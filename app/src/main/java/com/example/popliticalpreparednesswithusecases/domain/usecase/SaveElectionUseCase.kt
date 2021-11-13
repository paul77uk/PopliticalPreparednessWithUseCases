package com.example.popliticalpreparednesswithusecases.domain.usecase

import com.example.popliticalpreparednesswithusecases.data.model.Election
import com.example.popliticalpreparednesswithusecases.domain.repository.ElectionsRepository

class SaveElectionUseCase(private val electionsRepository: ElectionsRepository) {

    suspend fun execute(election: Election) = electionsRepository.saveElection(election)

}