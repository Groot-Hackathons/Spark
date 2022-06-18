package com.example.spark

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.spark.databinding.ChargingStationBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.firestore.GeoPoint
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class ChargingStations: AppCompatActivity(), OnMapReadyCallback {

    private lateinit var binding: ChargingStationBinding
    val db = Firebase.firestore

    private var places: ArrayList<LatLng> = ArrayList<LatLng>()
    var builder: LatLngBounds.Builder = LatLngBounds.Builder()
    val data = ArrayList<ItemsViewModel>()

    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ChargingStationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerView = binding.chargingRecyclerView



//        for (i in 1..20) {
//            data.add(ItemsViewModel("Station $i", "Rs 500/-", "-- KM", "4.2"))
//        }

//        val adapter = CustomAdapter(data)
//
//        recyclerView.adapter = adapter

        var linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager
        val mapFragment = supportFragmentManager.findFragmentById(
            R.id.map_fragment_charging
        ) as? SupportMapFragment

        places.add(LatLng(12.883295, 77.490052))
        places.add(LatLng(12.88, 55.77))
        mapFragment?.getMapAsync { googleMap ->
            getAllStations(googleMap)
        }

    }

    override fun onMapReady(p0: GoogleMap) {
        TODO("Not yet implemented")
    }

    private fun getAllStations(googleMap: GoogleMap): ArrayList<MutableMap<String, Any>> {
        println("Inside GET method")
        var stations = ArrayList<MutableMap<String,Any>>()
        db.collection("chargingStation")
            .get()
            .addOnSuccessListener { result ->
                Log.d("TAG", "Hello Kitty")
                for (document in result) {
                    Log.d("TAG", "${document.id} => ${document.data}")
                    println("Data is" + document.data)
                    stations.add(document.data)
                    data.add(
                        ItemsViewModel(
                            document.data.get("name").toString(),
                            document.data.get("price").toString(),
                            "-- KM",
                            document.data.get("rating").toString()
                        )
                    )
                    var pt: GeoPoint = document.data.get("location") as GeoPoint
                    var place = LatLng(pt.latitude, pt.longitude)
                    googleMap.addMarker(
                        MarkerOptions()
                            .title("Hello")
                            .position(place)
                    )
                    builder.include(place)
                }
                val adapter = CustomAdapter(data)

                recyclerView.adapter = adapter
                println("Stations is$stations")
                val bounds = builder.build()
                val cu = CameraUpdateFactory.newLatLngBounds(bounds, 100)
                googleMap.animateCamera(cu)
            }
            .addOnFailureListener { exception ->
                Log.w("TAG", "Error getting documents.", exception)
            }



//        addMarkers(googleMap, places)
        return stations
        // [END get_all_users]
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