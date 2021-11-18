package com.example.popliticalpreparednesswithusecases.data.repository.datasource

import com.example.popliticalpreparednesswithusecases.data.model.RepresentativeResponse
import retrofit2.Response

interface RepresentativeRemoteDataSource {

    suspend fun getRepresentatives(address: String): Response<RepresentativeResponse>

}