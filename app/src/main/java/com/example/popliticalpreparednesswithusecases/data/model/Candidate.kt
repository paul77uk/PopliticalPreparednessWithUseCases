package com.example.popliticalpreparednesswithusecases.data.model


import com.google.gson.annotations.SerializedName

data class Candidate(
    @SerializedName("candidateUrl")
    val candidateUrl: String,
    @SerializedName("channels")
    val channels: List<Channel>,
    @SerializedName("email")
    val email: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("party")
    val party: String,
    @SerializedName("phone")
    val phone: String
)