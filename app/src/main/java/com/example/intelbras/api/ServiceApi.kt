package com.example.intelbras.api

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET

interface ServiceApi {

    @GET("alarm-centrals")
    fun getAllAlarm() : Call<JsonObject>

}