package com.example.popliticalpreparednesswithusecases.data.repository.datasourceimpl

import com.example.popliticalpreparednesswithusecases.data.api.APIService
import com.example.popliticalpreparednesswithusecases.data.model.ElectionResponse
import com.example.popliticalpreparednesswithusecases.data.repository.datasource.ElectionRemoteDataSource
import retrofit2.Response

class ElectionRemoteDataSourceImpl(private val apiService: APIService) : ElectionRemoteDataSource {

    override suspend fun getElections(): Response<ElectionResponse> {
        return apiService.getElections()
    }

}