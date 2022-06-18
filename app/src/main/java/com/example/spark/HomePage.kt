package com.example.spark

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.spark.databinding.HomePageBinding

class HomePage: AppCompatActivity() {

    private lateinit var binding: HomePageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_page)
//        binding = HomePageBinding.inflate(layoutInflater)
//        setContentView(binding.root)
    }
}