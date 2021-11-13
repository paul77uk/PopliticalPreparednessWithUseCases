package com.example.popliticalpreparednesswithusecases.data.model


import com.google.gson.annotations.SerializedName

data class Channel(
    @SerializedName("id")
    val id: String,
    @SerializedName("type")
    val type: String
)