package com.example.spark

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.spark.databinding.ActivitySerialNumberBinding

class SerialNumber : AppCompatActivity() {

    private lateinit var binding: ActivitySerialNumberBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySerialNumberBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.serialSubmitBtn.setOnClickListener {
            var serialNumber = binding.serialNumberTextInput.toString()
            //TODO: Send serial number to database and link with the e-mail if possible

            val intent = Intent(this, HomePage::class.java)
            intent.putExtra("keyIdentifier", "value")
            startActivity(intent)
        }

    }



}