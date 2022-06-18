package com.example.spark

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.spark.databinding.ChargingStationBinding
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback

class ChargingStations: AppCompatActivity(), OnMapReadyCallback {

    private lateinit var binding: ChargingStationBinding

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        binding = ChargingStationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerView = binding.chargingRecyclerView

        val data = ArrayList<ItemsViewModel>()

        for (i in 1..20) {
            data.add(ItemsViewModel("Station $i", "Rs 500/-", "0.5KM", "4.2"))
        }

        val adapter = CustomAdapter(data)

        // Setting the Adapter with the recyclerview
        recyclerView.adapter = adapter

    }

    override fun onMapReady(p0: GoogleMap) {
        TODO("Not yet implemented")
    }
//    setContentView(binding.root)
}