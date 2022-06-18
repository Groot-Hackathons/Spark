package com.example.spark

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spark.databinding.ChargingStationBinding
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback

class ChargingStations: AppCompatActivity(), OnMapReadyCallback {

    private lateinit var binding: ChargingStationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ChargingStationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerView = binding.chargingRecyclerView

        val data = ArrayList<ItemsViewModel>()

        for (i in 1..20) {
            data.add(ItemsViewModel("Station $i", "Rs 500/-", "-- KM", "4.2"))
        }

        val adapter = CustomAdapter(data)

        recyclerView.adapter = adapter

        var linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager

    }

    override fun onMapReady(p0: GoogleMap) {
        TODO("Not yet implemented")
    }
}