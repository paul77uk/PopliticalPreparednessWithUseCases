package com.example.popliticalpreparednesswithusecases.domain.usecase

import com.example.popliticalpreparednesswithusecases.data.model.ElectionResponse
import com.example.popliticalpreparednesswithusecases.data.util.Resource
import com.example.popliticalpreparednesswithusecases.domain.repository.ElectionsRepository

class GetUpcomingElectionsUseCase(private val electionsRepository: ElectionsRepository) {

    suspend fun execute(): Resource<ElectionResponse> {
        return electionsRepository.getUpcomingElections()
    }

}