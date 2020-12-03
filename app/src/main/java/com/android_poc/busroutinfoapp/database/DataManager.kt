package com.android_poc.busroutinfoapp.database

import android.app.Application
import androidx.lifecycle.LiveData
import com.android_poc.busroutinfoapp.database.models.BusTimeEntity
import com.android_poc.busroutinfoapp.database.models.RouteInfoItem
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers

public class DataManager(application: Application) {
    private lateinit var busRoutDbObj: BusRoutInfoDBHelper

    init {
        busRoutDbObj = BusRoutInfoDBHelper.getInstance(application)
    }

    fun insertRoutInfoItems(routInfoItem: RouteInfoItem) {
        Completable.fromAction {
            busRoutDbObj.getRouteInfoItemDao().insertRoutInfoItemDao(routInfoItem)
        }.subscribeOn(Schedulers.io()).subscribe()

    }

    fun insertRouteTimings(busTimingPojo: BusTimeEntity){
       Completable.fromAction {
           busRoutDbObj.getBusTimingPojoDao().insertBusTimingData(busTimingPojo)
       }.subscribeOn(Schedulers.io()).subscribe()
    }

    fun getBusRouteInfoItemsFromDB() : LiveData<List<RouteInfoItem>>{
        return busRoutDbObj.getRouteInfoItemDao().allRoutInfoItems
    }

    fun getAllBusTimingPojosFromDB() : LiveData<List<BusTimeEntity>>{
        return busRoutDbObj.getBusTimingPojoDao().allBusTimingPojoFromDB
    }

    fun getBusTimingByGivenRouteFromDB(routeId:String):LiveData<List<BusTimeEntity>>{
        return busRoutDbObj.getBusTimingPojoDao().getAllBussesOnThisRouteId(routeId)
    }

}