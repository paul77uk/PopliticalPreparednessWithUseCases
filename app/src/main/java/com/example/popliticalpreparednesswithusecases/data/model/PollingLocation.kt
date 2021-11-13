package com.example.popliticalpreparednesswithusecases.data.model


import com.google.gson.annotations.SerializedName

data class PollingLocation(
    @SerializedName("address")
    val address: Address,
    @SerializedName("notes")
    val notes: String,
    @SerializedName("pollingHours")
    val pollingHours: String,
    @SerializedName("sources")
    val sources: List<Source>
)