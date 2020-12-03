package com.android_poc.busroutinfoapp.arch_component.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import com.android_poc.busroutinfoapp.arch_component.BusRoutInfoRepository
import com.android_poc.busroutinfoapp.database.models.BusTimeEntity
import com.android_poc.busroutinfoapp.database.models.RouteInfoItem
import com.android_poc.busroutinfoapp.utils.AppConstants
import com.android_poc.busroutinfoapp.utils.AppSharedPrefRepository

public class BusInfoViewModel: BaseViewModel() {

    /*fun fillRouteInfoItemTable(){
        Log.d("LOG","value of flag for RouteInfoTable is = "+ AppSharedPrefRepository.
        getInstance().getBoolean(AppConstants.FIRST_TIME_INSERTION_ROUTE_INFO_TBL,true))
        if(AppSharedPrefRepository.getInstance().getBoolean(
                AppConstants.FIRST_TIME_INSERTION_ROUTE_INFO_TBL,true)) {
            getBusRouteInfoRepository().insertDataToRouteInfoItemTable()
            AppSharedPrefRepository.getInstance().setBoolean(
                AppConstants.FIRST_TIME_INSERTION_ROUTE_INFO_TBL,false)
        }
    }
    fun fillBusTimingRouteTable(){
        Log.d("LOG","value of flag for BusTimingTable is = "+ AppSharedPrefRepository.
        getInstance().getBoolean(AppConstants.FIRST_TIME_INSERTION_BUS_TIMING_TBL,true))
        if(AppSharedPrefRepository.getInstance().getBoolean(
                AppConstants.FIRST_TIME_INSERTION_BUS_TIMING_TBL,true)){
            getBusRouteInfoRepository().insertBusRouteTimingIntoTable()
            AppSharedPrefRepository.getInstance().setBoolean(
                AppConstants.FIRST_TIME_INSERTION_BUS_TIMING_TBL,false)
        }
    }
*/
    fun getRouteInfoItemsFromRepo():LiveData<List<RouteInfoItem>>{
        return getBusRouteInfoRepository().getRouteInfoItemDataFromDB()
    }

    fun getBusTimingInfoFromRepo():LiveData<List<BusTimeEntity>>{
        return getBusRouteInfoRepository().getAllBusTimingPojosFromDB()
    }

    fun getBusTimingOnGivenRoute(routeId:String):LiveData<List<BusTimeEntity>>{
        return getBusRouteInfoRepository().getBusTimingFromRouteId(routeId)
    }

}