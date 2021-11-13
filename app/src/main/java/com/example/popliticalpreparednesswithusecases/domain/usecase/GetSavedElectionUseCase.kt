package com.example.popliticalpreparednesswithusecases.domain.usecase

import com.example.popliticalpreparednesswithusecases.data.model.Election
import com.example.popliticalpreparednesswithusecases.domain.repository.ElectionsRepository
import kotlinx.coroutines.flow.Flow

class GetSavedElectionUseCase(private val electionsRepository: ElectionsRepository) {

    fun execute(): Flow<List<Election>> {
        return electionsRepository.getSavedElection()
    }

}