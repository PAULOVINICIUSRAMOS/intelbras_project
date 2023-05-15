package com.example.intelbras.viewmodel

import androidx.lifecycle.ViewModel
import com.example.intelbras.api.ServiceApi
import com.example.intelbras.models.AlarmCentral
import com.example.intelbras.models.VideoDevice
import com.example.intelbras.network.ServiceProvider
import com.google.gson.GsonBuilder
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class MainActivityViewModel : ViewModel() {

    suspend fun getAlarmsCentral(): List<AlarmCentral> = suspendCoroutine { continuation ->
        val retrofit = ServiceProvider.getRetrofitInstance()
        val gson = GsonBuilder().create()
        val endpoint = retrofit.create(ServiceApi::class.java)
        endpoint.getAllAlarm().enqueue(object : Callback<JsonElement> {
            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                if (response.isSuccessful) {
                    val data = mutableListOf<AlarmCentral>()
                    response.body()?.asJsonObject?.get("data")?.asJsonArray?.forEach { jsonElement ->
                        val alarm = gson.fromJson(jsonElement, AlarmCentral::class.java)
                        data.add(alarm)
                    }
                    continuation.resume(data)
                } else {
                    continuation.resumeWithException(Exception("Requisição Falhou ${response.code()}"))
                }
            }

            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                continuation.resumeWithException(t)
            }
        })
    }

    suspend fun getVideoDevices(): List<VideoDevice> = suspendCoroutine { continuation ->
        val retrofit = ServiceProvider.getRetrofitInstance()
        val gson = GsonBuilder().create()
        val endpoint = retrofit.create(ServiceApi::class.java)
        endpoint.getAllVideoDevices().enqueue(object : Callback<JsonElement> {
            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                if (response.isSuccessful) {
                    val data = mutableListOf<VideoDevice>()
                    response.body()?.asJsonObject?.get("data")?.asJsonArray?.forEach { jsonElement ->
                        val video = gson.fromJson(jsonElement, VideoDevice::class.java)
                        data.add(video)
                    }
                    continuation.resume(data)
                } else {
                    continuation.resumeWithException(Exception("Requisição Falhou ${response.code()}"))
                }
            }

            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                continuation.resumeWithException(t)
            }
        })
    }
}