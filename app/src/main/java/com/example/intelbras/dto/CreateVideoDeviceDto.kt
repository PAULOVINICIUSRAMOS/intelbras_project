package com.example.intelbras.dto
import com.google.gson.annotations.SerializedName

data class CreateVideoDeviceDto(
    @SerializedName("name")
    val name: String,
    @SerializedName("serial")
    val serial: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("password")
    val password: String
) {
}