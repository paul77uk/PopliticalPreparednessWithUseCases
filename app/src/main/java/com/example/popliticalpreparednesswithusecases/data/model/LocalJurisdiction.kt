package com.example.popliticalpreparednesswithusecases.data.model


import com.google.gson.annotations.SerializedName

data class LocalJurisdiction(
    @SerializedName("electionAdministrationBody")
    val electionAdministrationBody: ElectionAdministrationBody,
    @SerializedName("name")
    val name: String,
    @SerializedName("sources")
    val sources: List<Source>
)