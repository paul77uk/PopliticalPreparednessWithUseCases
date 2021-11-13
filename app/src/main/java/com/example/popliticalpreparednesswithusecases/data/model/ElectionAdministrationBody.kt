package com.example.popliticalpreparednesswithusecases.data.model


import com.google.gson.annotations.SerializedName

data class ElectionAdministrationBody(
    @SerializedName("absenteeVotingInfoUrl")
    val absenteeVotingInfoUrl: String,
    @SerializedName("ballotInfoUrl")
    val ballotInfoUrl: String,
    @SerializedName("correspondenceAddress")
    val correspondenceAddress: CorrespondenceAddress,
    @SerializedName("electionInfoUrl")
    val electionInfoUrl: String,
    @SerializedName("electionRegistrationConfirmationUrl")
    val electionRegistrationConfirmationUrl: String,
    @SerializedName("electionRegistrationUrl")
    val electionRegistrationUrl: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("votingLocationFinderUrl")
    val votingLocationFinderUrl: String
)