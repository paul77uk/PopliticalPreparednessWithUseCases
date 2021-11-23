package com.example.popliticalpreparednesswithusecases.data.model

import com.google.gson.annotations.SerializedName

data class Representative (
        @SerializedName("official")
        val official: Official,
        @SerializedName("office")
        val office: Office
)