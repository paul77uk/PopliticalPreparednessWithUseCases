package com.example.popliticalpreparednesswithusecases.data.model


import com.google.gson.annotations.SerializedName

data class ElectionResponse(
    @SerializedName("elections")
    val elections: List<Election>,
    @SerializedName("kind")
    val kind: String
)