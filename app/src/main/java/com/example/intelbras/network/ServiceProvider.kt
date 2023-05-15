package com.example.intelbras.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ServiceProvider {

    private const val BASE_URL = "http://squadapps.ddns-intelbras.com.br:3000/"
    private const val token =
        "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6ImUzMDMwNjc0LTJhZmUtNDJkYi05ZmQyLWRkZTc2MzhlYzhhZiIsImlhdCI6MTY4MzY1NTk5MSwiZXhwIjoxNjg2MjQ3OTkxfQ.CHeKoL91H_A2Wez3PV400XViMILkFVJ7dtF7L-RUmpo"

    val httpClient = OkHttpClient.Builder()
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .addInterceptor { chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer $token")
            val request = requestBuilder.build()
            chain.proceed(request)
        }
        .build()

    fun getRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()
    }
}
