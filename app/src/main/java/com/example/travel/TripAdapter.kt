package com.example.travel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TripAdapter(private val tripList: List<Trip>) : RecyclerView.Adapter<TripAdapter.TripViewHolder>() {

    class TripViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val date: TextView = itemView.findViewById(R.id.tvDate)
        val image: ImageView = itemView.findViewById(R.id.imgLocation)
        val locationName: TextView = itemView.findViewById(R.id.tvLocationName)
        val time: TextView = itemView.findViewById(R.id.tvTime)
        val cost: TextView = itemView.findViewById(R.id.tvCost)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TripViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.trip_item, parent, false)
        return TripViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TripViewHolder, position: Int) {
        val currentItem = tripList[position]
        holder.date.text = currentItem.date
        holder.image.setImageResource(currentItem.imageResource)
        holder.locationName.text = currentItem.locationName
        holder.time.text = currentItem.time
        holder.cost.text = currentItem.cost
    }

    override fun getItemCount() = tripList.size
}
