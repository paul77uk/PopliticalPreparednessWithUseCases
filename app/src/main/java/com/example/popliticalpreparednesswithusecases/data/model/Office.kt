package com.example.popliticalpreparednesswithusecases.data.model


import com.google.gson.annotations.SerializedName

data class Office(
//    @SerializedName("divisionId")
//    val divisionId: String,
//    @SerializedName("levels")
//    val levels: List<String>,
    @SerializedName("name")
    var name: String,
    @SerializedName("officialIndices")
    val officialIndices: List<Int>,
//    @SerializedName("roles")
//    val roles: List<String>
)