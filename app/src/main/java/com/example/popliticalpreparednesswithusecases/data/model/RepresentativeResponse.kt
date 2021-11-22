package com.example.popliticalpreparednesswithusecases.data.model


import com.google.gson.annotations.SerializedName

data class RepresentativeResponse(
    @SerializedName("representatives")
    val representatives: List<Representative>,
    @SerializedName("divisions")
    val divisions: Divisions,
    @SerializedName("kind")
    val kind: String,
    @SerializedName("normalizedInput")
    val normalizedInput: NormalizedInput,
    @SerializedName("offices")
    val offices: List<Office>,
    @SerializedName("officials")
    val officials: List<Official>
)