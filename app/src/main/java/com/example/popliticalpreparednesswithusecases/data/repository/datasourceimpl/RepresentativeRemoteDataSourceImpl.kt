package com.example.popliticalpreparednesswithusecases.data.repository.datasourceimpl

import com.example.popliticalpreparednesswithusecases.data.api.APIService
import com.example.popliticalpreparednesswithusecases.data.model.RepresentativeResponse
import com.example.popliticalpreparednesswithusecases.data.repository.datasource.RepresentativeRemoteDataSource
import retrofit2.Response

class RepresentativeRemoteDataSourceImpl(private val apiService: APIService) :
    RepresentativeRemoteDataSource {

    override suspend fun getRepresentatives(address: String): Response<RepresentativeResponse> {
        return apiService.getRepresentatives(address = address)
    }

}