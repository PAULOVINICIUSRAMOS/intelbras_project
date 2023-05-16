package com.example.intelbras.ui.activity

import FormNewDeviceFragment
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.intelbras.R

class FormAddDeviceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_container)
        replaceFragment()
    }

    private fun replaceFragment() {
        val fragment = FormNewDeviceFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }
}