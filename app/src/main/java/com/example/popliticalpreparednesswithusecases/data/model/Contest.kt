package com.example.popliticalpreparednesswithusecases.data.model


import com.google.gson.annotations.SerializedName

data class Contest(
    @SerializedName("candidates")
    val candidates: List<Candidate>,
    @SerializedName("district")
    val district: District,
    @SerializedName("level")
    val level: List<String>,
    @SerializedName("office")
    val office: String,
    @SerializedName("referendumSubtitle")
    val referendumSubtitle: String,
    @SerializedName("referendumTitle")
    val referendumTitle: String,
    @SerializedName("referendumUrl")
    val referendumUrl: String,
    @SerializedName("roles")
    val roles: List<String>,
    @SerializedName("sources")
    val sources: List<Source>,
    @SerializedName("type")
    val type: String
)