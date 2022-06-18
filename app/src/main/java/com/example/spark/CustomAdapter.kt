package com.example.spark

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter(private val mList: List<ItemsViewModel>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.each_charging_station, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val itemsViewModel = mList[position]

        holder.stationNameTxt.text = itemsViewModel.stationName
        holder.distanceTxt.text = itemsViewModel.distance
        holder.ratingTxt.text = "⭐ ${itemsViewModel.ratings}"
        holder.priceTxt.text = itemsViewModel.price

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val stationNameTxt: TextView = itemView.findViewById(R.id.station_name_txt)
        val priceTxt: TextView = itemView.findViewById(R.id.price_txt)
        val ratingTxt: TextView = itemView.findViewById(R.id.rating_txt)
        val distanceTxt: TextView = itemView.findViewById(R.id.distance_txt)
    }
}