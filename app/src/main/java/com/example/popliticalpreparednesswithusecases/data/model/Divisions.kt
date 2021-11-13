package com.example.popliticalpreparednesswithusecases.data.model


import com.google.gson.annotations.SerializedName

data class Divisions(
    @SerializedName("ocd-division/country:us")
    val ocdDivisioncountryUs: OcdDivisioncountryUs,
    @SerializedName("ocd-division/country:us/state:ks")
    val ocdDivisioncountryUsstateKs: OcdDivisioncountryUsstateKs,
    @SerializedName("ocd-division/country:us/state:ks/county:wyandotte")
    val ocdDivisioncountryUsstateKscountyWyandotte: OcdDivisioncountryUsstateKscountyWyandotte,
    @SerializedName("ocd-division/country:us/state:ks/place:kansas_city")
    val ocdDivisioncountryUsstateKsplaceKansasCity: OcdDivisioncountryUsstateKsplaceKansasCity,

)