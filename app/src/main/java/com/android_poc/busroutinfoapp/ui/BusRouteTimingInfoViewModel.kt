package com.android_poc.busroutinfoapp.ui

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.android_poc.busroutinfoapp.arch_component.BusRoutInfoRepository
import com.android_poc.busroutinfoapp.arch_component.viewmodel.BaseViewModel
import com.android_poc.busroutinfoapp.database.models.BusTimeEntity

class BusRouteTimingInfoViewModel : BaseViewModel() {

    fun getAllBusRouteTimingByRouteId(routeId:String):LiveData<List<BusTimeEntity>>{
        return getBusRouteInfoRepository().getBusTimingFromRouteId(routeId)
    }
}