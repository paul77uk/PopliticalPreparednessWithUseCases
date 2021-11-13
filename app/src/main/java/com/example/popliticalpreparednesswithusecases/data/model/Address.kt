package com.example.popliticalpreparednesswithusecases.data.model


import com.google.gson.annotations.SerializedName

data class Address(
    @SerializedName("city")
    val city: String,
    @SerializedName("line1")
    val line1: String,
    @SerializedName("line2")
    val line2: String,
    @SerializedName("line3")
    val line3: String,
    @SerializedName("locationName")
    val locationName: String,
    @SerializedName("state")
    val state: String,
    @SerializedName("zip")
    val zip: String
)