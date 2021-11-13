package com.example.popliticalpreparednesswithusecases.data.model


import com.google.gson.annotations.SerializedName

data class CorrespondenceAddress(
    @SerializedName("city")
    val city: String,
    @SerializedName("line1")
    val line1: String,
    @SerializedName("state")
    val state: String,
    @SerializedName("zip")
    val zip: String
)