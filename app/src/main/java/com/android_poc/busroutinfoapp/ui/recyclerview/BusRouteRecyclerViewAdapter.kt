package com.android_poc.busroutinfoapp.ui.recyclerview

import android.content.Context
import android.view.LayoutInflater

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android_poc.busroutinfoapp.R
import com.android_poc.busroutinfoapp.database.models.RouteInfoItem

class BusRouteRecyclerViewAdapter(var routeInfoItemList:List<RouteInfoItem>,context: Context) :
    RecyclerView.Adapter<BusRouteRecyclerViewAdapter.BusRouteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusRouteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.bus_route_item,parent,false)
        return BusRouteViewHolder(view)
    }

    override fun onBindViewHolder(holder: BusRouteViewHolder, position: Int) {
        if(routeInfoItemList.get(position).name.isNotEmpty()){
            holder.tvBusName.text = routeInfoItemList.get(position).name
        }
        if(routeInfoItemList.get(position).source.isNotEmpty() &&
            routeInfoItemList.get(position).destination.isNotEmpty()) {
            holder.tvBusRoute.text =
                routeInfoItemList.get(position).source + " " + routeInfoItemList.get(position).destination
        }
        if(routeInfoItemList.get(position).tripDuration.isNotEmpty()){
            holder.tvBusRouteTime.text = routeInfoItemList.get(position).tripDuration
        }
    }


    override fun getItemCount(): Int {
        return routeInfoItemList.size
    }

    fun setBusRouteList(busRouteList:List<RouteInfoItem>){
        this.routeInfoItemList = busRouteList
        notifyDataSetChanged()
    }

    class BusRouteViewHolder(view:View) : RecyclerView.ViewHolder(view) {
        var tvBusName:TextView = view.findViewById(R.id.tvBusName)
        var tvBusRoute:TextView = view.findViewById(R.id.tvBusRoute)
        var tvBusRouteTime:TextView = view.findViewById(R.id.tvBusRouteTime)
    }
}