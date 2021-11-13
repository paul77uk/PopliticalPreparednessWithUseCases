package com.example.popliticalpreparednesswithusecases.domain.usecase

import com.example.popliticalpreparednesswithusecases.data.model.Election
import com.example.popliticalpreparednesswithusecases.domain.repository.ElectionsRepository

class UnfollowElectionUseCase(private val electionsRepository: ElectionsRepository) {

    suspend fun execute(election: Election) = electionsRepository.unfollowElection(election)

}