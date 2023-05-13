package com.example.intelbras.activity

import FormAddDeviceFragment
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.intelbras.R

class FormAddDeviceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_container)
        replaceFragment()
    }

    fun replaceFragment() {
        val fragment = FormAddDeviceFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }
}