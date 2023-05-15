package com.example.intelbras

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.intelbras.api.ServiceApi
import com.example.intelbras.databinding.ActivityMainBinding
import com.example.intelbras.network.ServiceProvider
import com.example.intelbras.ui.activity.FormAddDeviceActivity
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
        getAlarms()
    }

    fun getAlarms() {
        val retrofit = ServiceProvider.getRetrofitInstance()
        val endpoint = retrofit.create(ServiceApi::class.java)
        var value = mutableListOf<String>()
        endpoint.getAllAlarm().enqueue(object : Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                if (response.isSuccessful) {
                    var data = mutableListOf<String>()
                    response.body()?.keySet()?.iterator()?.forEach {
                        data.add(it)
                        value = data
                    }
                    println(data.count())
                }
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                println("ERROR")
            }
        })
        Toast.makeText(getApplicationContext(), value.get(1), Toast.LENGTH_SHORT).show();
    }

}