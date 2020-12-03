package com.android_poc.busroutinfoapp.arch_component

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import com.android_poc.busroutinfoapp.BusRouteAndTimeApplication
import com.android_poc.busroutinfoapp.database.DataManager
import com.android_poc.busroutinfoapp.database.models.BusTimeEntity
import com.android_poc.busroutinfoapp.database.models.BusTimingPojo
import com.android_poc.busroutinfoapp.database.models.RouteInfoItem
import com.android_poc.busroutinfoapp.utils.AppConstants
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers
import org.json.JSONObject
import java.io.InputStream

public class BusRoutInfoRepository private constructor(val application: Application) {
    private var dataManager: DataManager
    private var jsonObject: JSONObject
    private var mutableList = mutableListOf<String>()

    init {
        dataManager = DataManager(application)
        jsonObject = JSONObject(loadJsonFileFromAsset())
    }

    private object HOLDER{
        val INSTANCE = BusRoutInfoRepository(BusRouteAndTimeApplication.getApplicationInstance())
    }
    companion object{
        fun getBusRouteInfoRepositoryInstance():BusRoutInfoRepository{
            val instance : BusRoutInfoRepository by lazy { HOLDER.INSTANCE }
            return instance
        }
    }

    private fun loadJsonFileFromAsset(): String? {
        var json: String? = null
        try {
            val inputStream: InputStream = application.assets.open(AppConstants.FILE_NAME)
            json = inputStream.bufferedReader().use { it.readText() }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return json
    }

    fun insertDataToRouteInfoItemTable() {
        Completable.fromAction {
            val gson = Gson()
            val typeOfList = object : TypeToken<List<RouteInfoItem?>?>() {}.type
            val list = gson.fromJson<List<RouteInfoItem>>(
                jsonObject.getJSONArray(AppConstants.ROOT_OBJ).toString(), typeOfList
            )

            for (routInfoItem in list) {
                mutableList.add(routInfoItem.id)
                val routeInfoItemTbl = RouteInfoItem()
                routeInfoItemTbl.id = routInfoItem.id
                routeInfoItemTbl.destination = routInfoItem.destination
                routeInfoItemTbl.name = routInfoItem.name
                routeInfoItemTbl.source = routInfoItem.source
                routeInfoItemTbl.tripDuration = routInfoItem.tripDuration
                dataManager.insertRoutInfoItems(routeInfoItemTbl)


            }

        }.subscribeOn(Schedulers.io()).subscribe()
    }

    fun insertBusRouteTimingIntoTable(){
        val gson = Gson()
        val routeTimingsType =
            object : TypeToken<HashMap<String?, List<BusTimingPojo>?>>() {}.type
        val map: HashMap<String, List<BusTimingPojo>> = gson.fromJson(
            jsonObject.getJSONObject(AppConstants.ROOT_ROUTE_TIME_OBJ).toString(),
            routeTimingsType)
        for(key in mutableList) {
            val busTimingPojoList = map.get(key)
            Log.d("LOG", "" + key + " " + busTimingPojoList?.size)
            if (busTimingPojoList != null && busTimingPojoList.size > 0) {
                for (busTimingPojo in busTimingPojoList) {
                    val busTimeEntity = BusTimeEntity()
                    busTimeEntity.keyId = key
                    busTimeEntity.avaiable = busTimingPojo.avaiable
                    busTimeEntity.totalSeats = busTimingPojo.totalSeats
                    busTimeEntity.tripStartTime = busTimingPojo.tripStartTime
                    dataManager.insertRouteTimings(busTimeEntity)
                }
            }
        }
    }

    fun getRouteInfoItemDataFromDB(): LiveData<List<RouteInfoItem>> {
        return dataManager.getBusRouteInfoItemsFromDB()
    }

    fun getAllBusTimingPojosFromDB(): LiveData<List<BusTimeEntity>> {
        return dataManager.getAllBusTimingPojosFromDB()
    }

    fun getBusTimingFromRouteId(routeId:String):LiveData<List<BusTimeEntity>>{
        return dataManager.getBusTimingByGivenRouteFromDB(routeId)
    }

}