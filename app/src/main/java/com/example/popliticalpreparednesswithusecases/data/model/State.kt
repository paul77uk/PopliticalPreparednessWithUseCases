package com.example.popliticalpreparednesswithusecases.data.model


import com.google.gson.annotations.SerializedName

data class State(
    @SerializedName("electionAdministrationBody")
    val electionAdministrationBody: ElectionAdministrationBody,
    @SerializedName("local_jurisdiction")
    val localJurisdiction: LocalJurisdiction,
    @SerializedName("name")
    val name: String,
    @SerializedName("sources")
    val sources: List<Source>
)