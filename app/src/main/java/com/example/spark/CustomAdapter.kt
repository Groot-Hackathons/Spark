package com.example.spark

import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.razorpay.Checkout
import org.json.JSONObject

class CustomAdapter(private val mList: List<ItemsViewModel>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.each_charging_station, parent, false)

        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val itemsViewModel = mList[position]

        holder.stationNameTxt.text = itemsViewModel.stationName
        holder.distanceTxt.text = "~ " + itemsViewModel.distance + " KM"
        holder.ratingTxt.text = "⭐ ${itemsViewModel.ratings}"
        holder.priceTxt.text = "₹ ${itemsViewModel.price}/-"

        holder.itemView.setOnClickListener {
            var context = holder.stationNameTxt.context
            val intent = Intent(context, MainActivity::class.java)
            intent.putExtra("Amount", itemsViewModel.price.toInt())
            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val stationNameTxt: TextView = itemView.findViewById(R.id.station_name_txt)
        val priceTxt: TextView = itemView.findViewById(R.id.price_txt)
        val ratingTxt: TextView = itemView.findViewById(R.id.rating_txt)
        val distanceTxt: TextView = itemView.findViewById(R.id.distance_txt)
    }
}