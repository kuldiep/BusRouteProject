package com.android_poc.busroutinfoapp.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android_poc.busroutinfoapp.R
import com.android_poc.busroutinfoapp.arch_component.viewmodel.BusInfoViewModel
import com.android_poc.busroutinfoapp.arch_component.viewmodel.ViewModelFactory
import com.android_poc.busroutinfoapp.database.models.RouteInfoItem
import com.android_poc.busroutinfoapp.databinding.BusRouteTimingInfoFragmentBinding
import com.android_poc.busroutinfoapp.ui.recyclerview.BusRouteRecyclerViewAdapter
import com.android_poc.busroutinfoapp.ui.recyclerview.BusRouteTimingRecyclerAdapter
import com.android_poc.busroutinfoapp.utils.StartSnapHelper

class BusRouteTimingInfoFrag : Fragment() {
    private var binding: BusRouteTimingInfoFragmentBinding? = null
    var viewModelFactory = ViewModelFactory()
    private var busRouteInfoItemList: List<RouteInfoItem>? = null
    lateinit var busRouteTimingRecyclerAdapter: BusRouteTimingRecyclerAdapter

    companion object {
        fun newInstance() = BusRouteTimingInfoFrag()
    }

    private lateinit var viewModel: BusInfoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.bus_route_timing_info_fragment, container, false)
        binding = DataBindingUtil.bind(view)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(BusInfoViewModel::class.java)
        binding?.rvHeaderBusRoutes?.layoutManager = LinearLayoutManager(
            activity,
            LinearLayoutManager.HORIZONTAL, false
        )
        binding?.rvHeaderBusRoutes?.itemAnimator = DefaultItemAnimator()
        val busRouteRecyclerViewAdapter = BusRouteRecyclerViewAdapter(arrayListOf(), activity!!)
        busRouteTimingRecyclerAdapter = BusRouteTimingRecyclerAdapter(arrayListOf(), activity!!)
        binding?.rvHeaderBusRoutes?.adapter = busRouteRecyclerViewAdapter
        val startSnapHelper = StartSnapHelper()
        startSnapHelper.attachToRecyclerView(binding?.rvHeaderBusRoutes)
        binding?.rvHeaderBusRoutes?.addOnScrollListener(MyRecyclerViewOnScrollListener())

        binding?.rvBusRouteTimings?.layoutManager = LinearLayoutManager(activity)
        binding?.rvBusRouteTimings?.itemAnimator = DefaultItemAnimator()
        binding?.rvBusRouteTimings?.adapter = busRouteTimingRecyclerAdapter
        viewModel.getRouteInfoItemsFromRepo().observe(viewLifecycleOwner, {
            busRouteInfoItemList = it
            busRouteRecyclerViewAdapter.setBusRouteList(it)
        })
    }

    inner class MyRecyclerViewOnScrollListener : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)

            if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                val lm = recyclerView.layoutManager as LinearLayoutManager
                val position = lm.findFirstCompletelyVisibleItemPosition()
                if (position != -1) {
                    viewModel.getBusTimingOnGivenRoute(busRouteInfoItemList?.get(position)?.id!!)
                        .observe(viewLifecycleOwner,
                            Observer {
                                Log.d("LOG", "busEntity is = " + it)
                                if(it!=null && it.isNotEmpty()) {
                                    busRouteTimingRecyclerAdapter.setBusTimeEntityList(it)
                                    binding?.tvNoDataFound?.visibility = View.GONE
                                }else{
                                    busRouteTimingRecyclerAdapter.setBusTimeEntityList(it)
                                   
                                    binding?.tvNoDataFound?.visibility = View.VISIBLE
                                    binding?.tvNoDataFound?.text = "No Data Found"
                                }
                            })
                }
                Log.d("LOG", "position of visible item is = " + position)
            }
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

        }
    }


}