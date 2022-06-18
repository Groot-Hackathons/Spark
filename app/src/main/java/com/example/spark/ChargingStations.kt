package com.example.spark

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spark.databinding.ChargingStationBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions


class ChargingStations: AppCompatActivity(), OnMapReadyCallback {

    private lateinit var binding: ChargingStationBinding

    private var places: ArrayList<LatLng> = ArrayList<LatLng>()
    var builder: LatLngBounds.Builder = LatLngBounds.Builder()

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
        val mapFragment = supportFragmentManager.findFragmentById(
            R.id.map_fragment_charging
        ) as? SupportMapFragment

        places.add(LatLng(12.883295, 77.490052))
        places.add(LatLng(12.88, 55.77))

        mapFragment?.getMapAsync { googleMap ->
            addMarkers(googleMap, places)
        }

    }

    override fun onMapReady(p0: GoogleMap) {
        TODO("Not yet implemented")
    }

    private fun addMarkers(googleMap: GoogleMap, data: ArrayList<LatLng>) {
        data.forEach { place ->
            val marker = googleMap.addMarker(
                MarkerOptions()
                    .title("Hello")
                    .position(place)
            )
            builder.include(place)
//            googleMap.moveCamera(CameraUpdateFactory.newLatLng(place))
//            googleMap.animateCamera(CameraUpdateFactory.zoomTo(18.0f))
        }
        val bounds = builder.build()
        val cu = CameraUpdateFactory.newLatLngBounds(bounds, 100)
        googleMap.animateCamera(cu)
    }

}