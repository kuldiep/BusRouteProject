package com.android_poc.busroutinfoapp.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android_poc.busroutinfoapp.R

class BusRouteTimingInfoFrag : Fragment() {

    companion object {
        fun newInstance() = BusRouteTimingInfoFrag()
    }

    private lateinit var viewModel: BusRouteTimingInfoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bus_route_timing_info_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(BusRouteTimingInfoViewModel::class.java)
        // TODO: Use the ViewModel
    }

}