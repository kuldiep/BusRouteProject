package com.android_poc.busroutinfoapp

import android.app.Application
import android.util.Log
import com.android_poc.busroutinfoapp.arch_component.BusRoutInfoRepository
import com.android_poc.busroutinfoapp.utils.AppConstants
import com.android_poc.busroutinfoapp.utils.AppSharedPrefRepository

class BusRouteAndTimeApplication  : Application() {

    companion object {
        private lateinit var busRouteAndTimeApplication: BusRouteAndTimeApplication
        fun getApplicationInstance(): BusRouteAndTimeApplication {
            return busRouteAndTimeApplication
        }
    }

    override fun onCreate() {
        super.onCreate()
        busRouteAndTimeApplication = this
        Log.d("LOG", "onCreate() Called of BusRouteTimeApplication")
        AppSharedPrefRepository.getInstance().initPrefrence(this)
    }
}