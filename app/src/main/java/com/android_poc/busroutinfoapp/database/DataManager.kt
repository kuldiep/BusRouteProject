package com.android_poc.busroutinfoapp.database

import android.app.Application
import androidx.lifecycle.LiveData
import com.android_poc.busroutinfoapp.database.models.BusTimeEntity
import com.android_poc.busroutinfoapp.database.models.RouteInfoItem
import com.android_poc.busroutinfoapp.utils.AppConstants
import com.android_poc.busroutinfoapp.utils.AppSharedPrefRepository
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers

public class DataManager(application: Application) {
    private var busRoutDbObj: BusRoutInfoDBHelper

    init {
        busRoutDbObj = BusRoutInfoDBHelper.getInstance(application)
    }

    fun insertRoutInfoItems(routInfoItemList: List<RouteInfoItem>) {

        Completable.fromAction {
            for (routInfoObj in routInfoItemList)
                busRoutDbObj.getRouteInfoItemDao().insertRoutInfoItemDao(routInfoObj)
        }.subscribeOn(Schedulers.io()).doOnComplete {
            AppSharedPrefRepository.getInstance().setBoolean(
                AppConstants.FIRST_TIME_INSERTION_ROUTE_INFO_TBL,false)
        }.subscribe()

    }

    fun insertRouteTimings(busTimingEntityList: List<BusTimeEntity>) {
        Completable.fromAction {
            for (busTimingEntity in busTimingEntityList)
                busRoutDbObj.getBusTimingPojoDao().insertBusTimingData(busTimingEntity)
        }.subscribeOn(Schedulers.io()).doOnComplete {
            AppSharedPrefRepository.getInstance().setBoolean(
                AppConstants.FIRST_TIME_INSERTION_BUS_TIMING_TBL,false)
        }.subscribe()
    }

    fun getBusRouteInfoItemsFromDB() : LiveData<List<RouteInfoItem>>{
        return busRoutDbObj.getRouteInfoItemDao().allRoutInfoItems
    }

    fun getAllBusTimingPojosFromDB() : LiveData<List<BusTimeEntity>>{
        return busRoutDbObj.getBusTimingPojoDao().allBusTimingPojoFromDB
    }

    fun getBusTimingByGivenRouteFromDB(routeId:String,currentTime:Int):LiveData<List<BusTimeEntity>>{
        return busRoutDbObj.getBusTimingPojoDao().getAllBussesOnThisRouteId(routeId,currentTime)
    }

}