package com.example.popliticalpreparednesswithusecases.data.api

import com.example.popliticalpreparednesswithusecases.BuildConfig
import com.example.popliticalpreparednesswithusecases.data.model.ElectionResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {

    @GET("elections")
    suspend fun getElections(
        @Query("key")
        key: String = BuildConfig.API_KEY
    ): Response<ElectionResponse>

}