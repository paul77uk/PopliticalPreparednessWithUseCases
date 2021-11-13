package com.example.popliticalpreparednesswithusecases.data.model


import com.google.gson.annotations.SerializedName

data class ElectionOfficial(
    @SerializedName("emailAddress")
    val emailAddress: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("officePhoneNumber")
    val officePhoneNumber: String,
    @SerializedName("title")
    val title: String
)