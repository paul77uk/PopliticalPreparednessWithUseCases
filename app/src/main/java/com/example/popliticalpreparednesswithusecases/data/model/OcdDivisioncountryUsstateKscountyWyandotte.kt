package com.example.popliticalpreparednesswithusecases.data.model


import com.google.gson.annotations.SerializedName

data class OcdDivisioncountryUsstateKscountyWyandotte(
    @SerializedName("name")
    val name: String,
    @SerializedName("officeIndices")
    val officeIndices: List<Int>
)