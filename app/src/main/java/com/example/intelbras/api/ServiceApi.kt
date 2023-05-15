package com.example.intelbras.api

import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET

interface ServiceApi {

    @GET("alarm-centrals")
    fun getAllAlarm() : Call<JsonElement>

    @GET("video-devices")
    fun getAllVideoDevices() : Call<JsonElement>

}