package com.example.popliticalpreparednesswithusecases.domain.repository

import com.example.popliticalpreparednesswithusecases.data.model.RepresentativeResponse
import com.example.popliticalpreparednesswithusecases.data.util.Resource

interface RepresentativeRepository {

    suspend fun getSearchedRepresentatives(searchQuery: String) : Resource<RepresentativeResponse>

}