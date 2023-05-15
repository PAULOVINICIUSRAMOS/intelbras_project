package com.example.intelbras

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.intelbras.api.ServiceApi
import com.example.intelbras.databinding.ActivityMainBinding
import com.example.intelbras.network.ServiceProvider
import com.example.intelbras.ui.activity.FormAddDeviceActivity
import com.google.gson.JsonObject
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.fabBtnAdd.setOnClickListener {
            val intent = Intent(this, FormAddDeviceActivity::class.java)
            startActivity(intent)
        }

        lifecycleScope.launch {
            try {
                val alarms = getAlarms()
                Toast.makeText(getApplicationContext(), alarms[1], Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Toast.makeText(getApplicationContext(), "ERROR: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    suspend fun getAlarms(): List<String> = suspendCoroutine { continuation ->
        val retrofit = ServiceProvider.getRetrofitInstance()
        val endpoint = retrofit.create(ServiceApi::class.java)
        endpoint.getAllAlarm().enqueue(object : Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                if (response.isSuccessful) {
                    val data = mutableListOf<String>()
                    response.body()?.keySet()?.iterator()?.forEach {
                        data.add(it)
                    }
                    continuation.resume(data)
                } else {
                    continuation.resumeWithException(Exception("Request failed with code ${response.code()}"))
                }
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                continuation.resumeWithException(t)
            }
        })
    }

}