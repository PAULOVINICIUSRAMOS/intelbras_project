package com.example.intelbras

import DeviceListAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.intelbras.databinding.ActivityMainBinding
import com.example.intelbras.ui.activity.FormAddDeviceActivity

import com.example.intelbras.models.AlarmCentral
import com.example.intelbras.models.VideoDevice
import com.example.intelbras.viewmodel.MainActivityViewModel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainActivityViewModel by viewModels()
    private lateinit var devicesAdapter: DeviceListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        devicesAdapter = DeviceListAdapter()

        binding.fabBtnAdd.setOnClickListener {
            val intent = Intent(this, FormAddDeviceActivity::class.java)
            startActivity(intent)
        }

        lifecycleScope.launch {
            try {
                val alarms = viewModel.getAlarmsCentral()
                val video = viewModel.getVideoDevices()
                val combinedList = mutableListOf<Any>()
                combinedList.addAll(alarms)
                combinedList.addAll(video)
                val namesList = combinedList.map { device ->
                    when (device) {
                        is AlarmCentral -> device.name
                        is VideoDevice -> device.name
                        else -> throw IllegalArgumentException("ERROR")
                    }
                }
                devicesAdapter.submitList(namesList)
                binding.listItems.layoutManager = LinearLayoutManager(this@MainActivity)
                binding.listItems.adapter = devicesAdapter
            } catch (e: Exception) {
                Toast.makeText(applicationContext, "ERROR: ${e.message}", Toast.LENGTH_LONG).show()
                Log.e("ERROR", e.message, e)
            }
        }
    }
}
