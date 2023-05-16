package com.example.intelbras.api.service

import com.example.intelbras.dto.CreateAlarmCentralDto
import com.example.intelbras.dto.CreateVideoDeviceDto
import com.google.gson.JsonElement
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ServiceApi {

    @GET("alarm-centrals")
    fun getAllAlarm() : Call<JsonElement>

    @GET("video-devices")
    fun getAllVideoDevices() : Call<JsonElement>

    @POST("video-devices")
    fun createVideoDevices(@Body createVideoDeviceDto: CreateVideoDeviceDto): Call<ResponseBody>

    @POST("alarm-centrals")
    fun createAlarmeCentral(@Body createAlarmeCentralDto: CreateAlarmCentralDto): Call<ResponseBody>

}