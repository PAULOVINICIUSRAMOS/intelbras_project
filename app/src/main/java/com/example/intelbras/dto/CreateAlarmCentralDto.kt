package com.example.intelbras.dto
import com.google.gson.annotations.SerializedName

data class CreateAlarmCentralDto(
    @SerializedName("name")
    val name: String,
    @SerializedName("macAddress")
    val macAddress: String,
    @SerializedName("password")
    val password: String
) {
}