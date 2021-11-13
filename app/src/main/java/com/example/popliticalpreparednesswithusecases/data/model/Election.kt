package com.example.popliticalpreparednesswithusecases.data.model


import com.google.gson.annotations.SerializedName

data class Election(
    @SerializedName("electionDay")
    val electionDay: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("ocdDivisionId")
    val ocdDivisionId: String
)