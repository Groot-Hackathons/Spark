package com.example.spark

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.spark.databinding.HomePageBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class HomePage: AppCompatActivity(), OnMapReadyCallback{

    private lateinit var binding: HomePageBinding
//    private lateinit var currentLocation: Location
//    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private val permissionCode = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = HomePageBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onMapReady(p0: GoogleMap) {
        TODO("Not yet implemented")
    }

//    override fun onMapReady(googleMap: GoogleMap) {
//        val latLng = LatLng(currentLocation.latitude, currentLocation.longitude)
//        val markerOptions = MarkerOptions().position(latLng).title("Your car is here")
//        googleMap?.animateCamera(CameraUpdateFactory.newLatLng(latLng))
//        googleMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 5f))
//        googleMap?.addMarker(markerOptions)
//    }
//
//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        when (requestCode) {
//            permissionCode -> if (grantResults.isNotEmpty() && grantResults[0] ==
//            PackageManager.PERMISSION_GRANTED) {
//            fetchLocation()
//        }
//        }
//    }
//
//    private fun fetchLocation() {
//        if (ActivityCompat.checkSelfPermission(
//                this, Manifest.permission.ACCESS_FINE_LOCATION) !=
//            PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
//                this, Manifest.permission.ACCESS_COARSE_LOCATION) !=
//            PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(this,
//                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), permissionCode)
//            return
//        }
//        val task = fusedLocationProviderClient.lastLocation
//        task.addOnSuccessListener { location ->
//            if (location != null) {
//                currentLocation = location
//                Toast.makeText(applicationContext, currentLocation.latitude.toString() + "" +
//                        currentLocation.longitude, Toast.LENGTH_SHORT).show()
//                val supportMapFragment = (supportFragmentManager.findFragmentById(R.id.map_fragment) as
//                        SupportMapFragment?)!!
//                supportMapFragment.getMapAsync(this@HomePage)
//            }
//        }
//    }

}