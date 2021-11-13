package com.example.popliticalpreparednesswithusecases.data.model


import com.google.gson.annotations.SerializedName

data class VoterInfoResponse(
    @SerializedName("contests")
    val contests: List<Contest>,
    @SerializedName("election")
    val election: Election,
    @SerializedName("kind")
    val kind: String,
    @SerializedName("normalizedInput")
    val normalizedInput: NormalizedInput,
    @SerializedName("pollingLocations")
    val pollingLocations: List<PollingLocation>,
    @SerializedName("state")
    val state: List<State>
)