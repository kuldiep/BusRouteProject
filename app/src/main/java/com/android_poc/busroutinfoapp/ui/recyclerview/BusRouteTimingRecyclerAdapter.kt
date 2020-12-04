package com.android_poc.busroutinfoapp.ui.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android_poc.busroutinfoapp.R
import com.android_poc.busroutinfoapp.database.models.BusTimeEntity
import com.android_poc.busroutinfoapp.utils.AppUtils

class BusRouteTimingRecyclerAdapter(var busRouteTimingList: List<BusTimeEntity>, context: Context) :
    RecyclerView.Adapter<BusRouteTimingRecyclerAdapter.BusRouteTimingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusRouteTimingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.bus_route_timings_item, parent, false
        )
        return BusRouteTimingViewHolder(view)
    }

    override fun onBindViewHolder(holder: BusRouteTimingViewHolder, position: Int) {
        if (busRouteTimingList.get(position).tripStartTime!=null) {
            holder.tvBusStartTime.text =
                "Start Time : " + AppUtils.getTimeAndDate(busRouteTimingList.get(
                    position).tripStartTime.toLong(),"hh:mm aa")
        }
        holder.tvBusAvailableSeats.text ="Avl : "
            busRouteTimingList.get(position).avaiable.toString() + " / " + busRouteTimingList.get(
                position
            ).totalSeats.toString()
    }

    override fun getItemCount(): Int {
        return busRouteTimingList.size
    }

    fun setBusTimeEntityList(busRouteTimingList: List<BusTimeEntity>) {
        this.busRouteTimingList = busRouteTimingList
        notifyDataSetChanged()
    }

    class BusRouteTimingViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvBusStartTime: TextView = view.findViewById(R.id.tvBusStartTime)
        var tvBusAvailableSeats: TextView = view.findViewById(R.id.tvBusAvailableSeats)
    }
}