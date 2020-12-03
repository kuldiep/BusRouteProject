package com.android_poc.busroutinfoapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction

import com.android_poc.busroutinfoapp.R
import com.android_poc.busroutinfoapp.utils.AppConstants

class BusRouteViewsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bus_route_views)
        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameContainer, BusRouteTimingInfoFrag.newInstance(), AppConstants.BUS_ROUTE_FRAG_TAG)
        fragmentTransaction.addToBackStack(AppConstants.BUS_ROUTE_FRAG_TAG)
        fragmentTransaction.commit()
    }
}