package com.example.popliticalpreparednesswithusecases.data.repository.datasource

import com.example.popliticalpreparednesswithusecases.data.model.ElectionResponse
import retrofit2.Response

interface ElectionRemoteDataSource {

    suspend fun getElections(): Response<ElectionResponse>

}