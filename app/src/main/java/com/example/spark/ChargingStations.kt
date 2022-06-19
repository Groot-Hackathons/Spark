package com.example.spark

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.spark.databinding.ChargingStationBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.GeoPoint
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.json.JSONObject


class ChargingStations: AppCompatActivity(), OnMapReadyCallback {

    private lateinit var binding: ChargingStationBinding
    val db = Firebase.firestore

    private var places: ArrayList<LatLng> = ArrayList<LatLng>()
    var builder: LatLngBounds.Builder = LatLngBounds.Builder()
    val data = ArrayList<ItemsViewModel>()

    lateinit var recyclerView: RecyclerView

    private var yourCarLocation: LatLng = LatLng(12.88, 77.49)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ChargingStationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerView = binding.chargingRecyclerView

        var linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager


        val mapFragment = supportFragmentManager.findFragmentById(
            R.id.map_fragment_charging
        ) as? SupportMapFragment

        mapFragment?.getMapAsync { googleMap ->
            getAllStations(googleMap)
        }
        var intent: Intent = intent
        yourCarLocation = LatLng(
            intent.getStringExtra("Latitude")?.toDouble() ?: 12.883295,
            intent.getStringExtra("Longitude")?.toDouble() ?: 77.490052
        )
    }

    override fun onMapReady(p0: GoogleMap) {
        TODO("Not yet implemented")
    }

    private fun resizeMapIcons(iconName: String?, width: Int, height: Int): Bitmap {
        val imageBitmap = BitmapFactory.decodeResource(
            resources, resources.getIdentifier(
                iconName, "drawable",
                packageName
            )
        )
        return Bitmap.createScaledBitmap(imageBitmap, width, height, false)
    }

    private fun getAllStations(googleMap: GoogleMap): ArrayList<MutableMap<String, Any>> {
        println("Inside GET method")
        var stations = ArrayList<MutableMap<String,Any>>()

        db.collection("chargingStation")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d("TAG", "${document.id} => ${document.data}")
                    println("Data is" + document.data)
                    stations.add(document.data)
                    var pt: GeoPoint = document.data.get("location") as GeoPoint
                    var place = LatLng(pt.latitude, pt.longitude)
                    val distance = FloatArray(1)
                    Location.distanceBetween(
                        yourCarLocation.latitude,
                        yourCarLocation.longitude,
                        place.latitude,
                        place.longitude,
                        distance
                    )
                    data.add(
                        ItemsViewModel(
                            document.data.get("name").toString(),
                            document.data.get("price").toString(),
                            "%.2f".format(distance[0]/1000),
                            document.data.get("rating").toString()
                        )
                    )

                    var _bitmap = resizeMapIcons("charger", 100, 100)
                    if (document.data.get("type") == "HOME"){
                        _bitmap = resizeMapIcons("home_charger", 100, 100)
                    }
                    googleMap.addMarker(
                        MarkerOptions()
                            .title(document.data.get("name").toString())
                            .icon(BitmapDescriptorFactory.fromBitmap(_bitmap))
                            .position(place)
                    )
                    builder.include(place)
                }
                val adapter = CustomAdapter(data)

                recyclerView.adapter = adapter

                var _bitmap = resizeMapIcons("car_top", 100, 50)
                googleMap.addMarker(
                    MarkerOptions()
                        .title("Your Car")
                        .icon(BitmapDescriptorFactory.fromBitmap(_bitmap))
                        .position(yourCarLocation)
                )

                builder.include(yourCarLocation)

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


}