package com.example.popliticalpreparednesswithusecases.data.model


import com.google.gson.annotations.SerializedName

data class Source(
    @SerializedName("name")
    val name: String,
    @SerializedName("official")
    val official: Boolean
)