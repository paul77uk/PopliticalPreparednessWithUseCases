package com.example.popliticalpreparednesswithusecases.data.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "elections")
data class Election(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("electionDay")
    val electionDay: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("ocdDivisionId")
    val ocdDivisionId: String
) : Serializable