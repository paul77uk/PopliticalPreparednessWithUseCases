package com.example.popliticalpreparednesswithusecases.domain.usecase

import com.example.popliticalpreparednesswithusecases.data.model.RepresentativeResponse
import com.example.popliticalpreparednesswithusecases.data.util.Resource
import com.example.popliticalpreparednesswithusecases.domain.repository.RepresentativeRepository

class GetSearchedRepresentativeUseCase(private val representativeRepository: RepresentativeRepository) {

    suspend fun execute(searchQuery: String): Resource<RepresentativeResponse> {
        return representativeRepository.getSearchedRepresentatives(searchQuery)
    }

}