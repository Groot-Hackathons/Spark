package com.example.spark

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.spark.databinding.SettingsBinding

class Settings: AppCompatActivity() {

    private lateinit var binding: SettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.logout.setOnClickListener {

        }

    }

}