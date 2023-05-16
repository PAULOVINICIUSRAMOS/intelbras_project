package com.example.intelbras.models
import com.google.gson.annotations.SerializedName

data class AlarmCentral(
    @SerializedName("name")
    val name: String,
    @SerializedName("macAddress")
    val macAddress: String,
    @SerializedName("password")
    val password: String
) {
}