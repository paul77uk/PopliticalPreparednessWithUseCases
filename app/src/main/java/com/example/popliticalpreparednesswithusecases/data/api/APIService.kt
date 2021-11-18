package com.example.popliticalpreparednesswithusecases.data.api

import com.example.popliticalpreparednesswithusecases.BuildConfig
import com.example.popliticalpreparednesswithusecases.data.model.ElectionResponse
import com.example.popliticalpreparednesswithusecases.data.model.RepresentativeResponse
import com.example.popliticalpreparednesswithusecases.data.model.VoterInfoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {

    @GET("elections")
    suspend fun getElections(
        @Query("key")
        key: String = BuildConfig.API_KEY
    ): Response<ElectionResponse>

    @GET("voteInfo")
    suspend fun getVoterInfo(
        @Query("key")
        key: String = BuildConfig.API_KEY,
        @Query("address")
        address: String = "1263 Pacific Ave. Kansas City",
        @Query("electionId")
        electionId: String
    ): Response<VoterInfoResponse>

    @GET("representatives")
    suspend fun getRepresentatives(
        @Query("key")
        key: String = BuildConfig.API_KEY,
        @Query("address")
        address: String
    ): Response<RepresentativeResponse>

}