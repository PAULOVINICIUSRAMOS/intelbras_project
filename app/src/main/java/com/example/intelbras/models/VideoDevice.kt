package com.example.intelbras.models
import com.google.gson.annotations.SerializedName

data class VideoDevice(
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