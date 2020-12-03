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
        Log.d(
            "LOG",
            "value of flag for RouteInfoTable is = " + AppSharedPrefRepository.getInstance()
                .getBoolean(AppConstants.FIRST_TIME_INSERTION_ROUTE_INFO_TBL, true)
        )
        if (AppSharedPrefRepository.getInstance().getBoolean(
                AppConstants.FIRST_TIME_INSERTION_ROUTE_INFO_TBL, true)) {
            BusRoutInfoRepository.getBusRouteInfoRepositoryInstance()
                .insertDataToRouteInfoItemTable()
            AppSharedPrefRepository.getInstance().setBoolean(
                AppConstants.FIRST_TIME_INSERTION_ROUTE_INFO_TBL, false)
        }
        Log.d(
            "LOG",
            "value of flag for BusTimingTable is = " + AppSharedPrefRepository.getInstance()
                .getBoolean(AppConstants.FIRST_TIME_INSERTION_BUS_TIMING_TBL, true))
        if (AppSharedPrefRepository.getInstance().getBoolean(
                AppConstants.FIRST_TIME_INSERTION_BUS_TIMING_TBL, true)) {

            BusRoutInfoRepository.getBusRouteInfoRepositoryInstance()
                .insertBusRouteTimingIntoTable()
            AppSharedPrefRepository.getInstance().setBoolean(
                AppConstants.FIRST_TIME_INSERTION_BUS_TIMING_TBL, false)
        }
    }
}