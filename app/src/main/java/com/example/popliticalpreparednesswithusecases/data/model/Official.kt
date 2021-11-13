package com.example.popliticalpreparednesswithusecases.data.model


import com.google.gson.annotations.SerializedName

data class Official(
    @SerializedName("address")
    val address: List<Address>,
    @SerializedName("channels")
    val channels: List<Channel>,
    @SerializedName("emails")
    val emails: List<String>,
    @SerializedName("name")
    val name: String,
    @SerializedName("party")
    val party: String,
    @SerializedName("phones")
    val phones: List<String>,
    @SerializedName("photoUrl")
    val photoUrl: String,
    @SerializedName("urls")
    val urls: List<String>
)