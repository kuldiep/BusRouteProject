package com.android_poc.busroutinfoapp.arch_component.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import com.android_poc.busroutinfoapp.arch_component.BusRoutInfoRepository
import com.android_poc.busroutinfoapp.database.models.BusTimeEntity
import com.android_poc.busroutinfoapp.database.models.RouteInfoItem
import com.android_poc.busroutinfoapp.utils.AppConstants
import com.android_poc.busroutinfoapp.utils.AppSharedPrefRepository

class BusInfoViewModel: BaseViewModel() {


    fun getRouteInfoItemsFromRepo():LiveData<List<RouteInfoItem>>{
        return getBusRouteInfoRepository().getRouteInfoItemDataFromDB()
    }

    fun getBusTimingInfoFromRepo():LiveData<List<BusTimeEntity>>{
        return getBusRouteInfoRepository().getAllBusTimingPojosFromDB()
    }

    fun getBusTimingOnGivenRoute(routeId:String,currentTime:Int):LiveData<List<BusTimeEntity>>{
        return getBusRouteInfoRepository().getBusTimingFromRouteId(routeId,currentTime)
    }

}