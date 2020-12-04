package com.android_poc.busroutinfoapp.arch_component

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import com.android_poc.busroutinfoapp.BusRouteAndTimeApplication
import com.android_poc.busroutinfoapp.database.DataManager
import com.android_poc.busroutinfoapp.database.models.BusTimeEntity
import com.android_poc.busroutinfoapp.database.models.BusTimingPojo
import com.android_poc.busroutinfoapp.database.models.RouteInfoItem
import com.android_poc.busroutinfoapp.utils.AppConstants
import com.android_poc.busroutinfoapp.utils.AppSharedPrefRepository
import com.android_poc.busroutinfoapp.utils.AppUtils
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONObject

 class BusRoutInfoRepository private constructor(val application: Application) {
    private var dataManager: DataManager

    init {
        dataManager = DataManager(application)
        storeAndParseJson()
    }

    private object HOLDER {
        val INSTANCE = BusRoutInfoRepository(BusRouteAndTimeApplication.getApplicationInstance())
    }

    companion object {
        fun getBusRouteInfoRepositoryInstance(): BusRoutInfoRepository {
            val instance: BusRoutInfoRepository by lazy { HOLDER.INSTANCE }
            return instance
        }
    }

    fun storeAndParseJson() {
        if (AppSharedPrefRepository.getInstance()
                .getBoolean(AppConstants.FIRST_TIME_INSERTION_ROUTE_INFO_TBL, true)
            && AppSharedPrefRepository.getInstance()
                .getBoolean(AppConstants.FIRST_TIME_INSERTION_BUS_TIMING_TBL, true)
        ) {
            val jsonObject = AppUtils.loadJsonFileFromAsset(AppConstants.BUS_JSON_FILE)
            insertDataToRouteInfoItemTable(getRouteInfoFromJson(jsonObject!!))
            insertBusRouteTimingIntoTable(getBusRouteTimingFromJson(jsonObject))
        }
    }

    fun getRouteInfoFromJson(jsonObject: JSONObject): List<RouteInfoItem> {
        val typeOfList = object : TypeToken<List<RouteInfoItem?>?>() {}.type
        val list = Gson().fromJson<List<RouteInfoItem>>(
            jsonObject.getJSONArray(AppConstants.ROOT_OBJ).toString(), typeOfList
        )
        return list
    }

    fun insertDataToRouteInfoItemTable(list: List<RouteInfoItem>) {
        dataManager.insertRoutInfoItems(list)
    }

    fun getBusRouteTimingFromJson(jsonObject: JSONObject): Map<String, List<BusTimingPojo>> {
        val routeTimingsType =
            object : TypeToken<HashMap<String?, List<BusTimingPojo>?>>() {}.type
        val map: HashMap<String, List<BusTimingPojo>> = Gson().fromJson(
            jsonObject.getJSONObject(AppConstants.ROOT_ROUTE_TIME_OBJ).toString(),
            routeTimingsType
        )
        return map
    }


    fun insertBusRouteTimingIntoTable(map: Map<String, List<BusTimingPojo>>) {
        val busTimingEntityList = arrayListOf<BusTimeEntity>()
        for ((key, busTimingPojoList) in map) {
            if (busTimingPojoList.isNotEmpty()) {
                for (busTimingPojo in busTimingPojoList) {
                    BusTimeEntity().apply {
                        keyId = key
                        avaiable = busTimingPojo.avaiable
                        totalSeats = busTimingPojo.totalSeats
                        tripStartTime = AppUtils.getMiliSecondsFromString(busTimingPojo.tripStartTime)
                        busTimingEntityList.add(this)
                    }

                }
            }
        }
        dataManager.insertRouteTimings(busTimingEntityList)
    }

    fun getRouteInfoItemDataFromDB(): LiveData<List<RouteInfoItem>> {
        return dataManager.getBusRouteInfoItemsFromDB()
    }

    fun getAllBusTimingPojosFromDB(): LiveData<List<BusTimeEntity>> {
        return dataManager.getAllBusTimingPojosFromDB()
    }

    fun getBusTimingFromRouteId(routeId: String,currentTime:Int): LiveData<List<BusTimeEntity>> {
        return dataManager.getBusTimingByGivenRouteFromDB(routeId,currentTime)
    }

}