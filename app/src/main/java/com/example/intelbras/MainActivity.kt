package com.example.intelbras
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.intelbras.activity.FormAddDeviceActivity
import com.example.intelbras.databinding.ActivityMainBinding

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
    }
}