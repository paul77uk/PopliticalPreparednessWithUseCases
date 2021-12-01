package com.example.popliticalpreparednesswithusecases.data.model

import com.google.gson.annotations.SerializedName

data class Representative (
        @SerializedName("official")
        var official: Official,
        @SerializedName("office")
        var office: Office
)