package com.syedsubahani.workspacehub.ui.roomscreen

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.syedsubahani.workspacehub.R
import com.syedsubahani.workspacehub.data.models.RoomsResponseItem


public class RoomsListAdapter(
    private var roomsList: ArrayList<RoomsResponseItem>,
    var context: Context
) : RecyclerView.Adapter<RoomsListAdapter.RoomsViewHolder>() {

    class RoomsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var roomCapacityTextView: TextView = itemView.findViewById(R.id.roomCapacity)
        var roomStatusTextView: TextView = itemView.findViewById(R.id.roomStatus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomsViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_room, parent, false)
        return RoomsViewHolder(view)
    }

    override fun onBindViewHolder(holder: RoomsViewHolder, position: Int) {
        holder.roomCapacityTextView.text =
            roomsList[position].maxOccupancy

        if(roomsList[position].isOccupied) {
            holder.roomStatusTextView.text = "Occupied"
            holder.roomStatusTextView.setTextColor(Color.RED)
        } else {
            holder.roomStatusTextView.text = "Available"
            holder.roomStatusTextView.setTextColor(Color.GRAY)
        }

    }

    override fun getItemCount(): Int {
        return roomsList.size
    }
}