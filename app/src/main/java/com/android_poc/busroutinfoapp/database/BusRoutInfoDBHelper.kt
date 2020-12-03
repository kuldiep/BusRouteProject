package com.android_poc.busroutinfoapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.android_poc.busroutinfoapp.database.daos.BusTimeEntityDao
import com.android_poc.busroutinfoapp.database.daos.RoutInfoItemDao
import com.android_poc.busroutinfoapp.database.models.BusTimeEntity
import com.android_poc.busroutinfoapp.database.models.RouteInfoItem

@Database(entities = arrayOf(RouteInfoItem::class,
    BusTimeEntity::class),version = 1,exportSchema = false)
abstract class BusRoutInfoDBHelper : RoomDatabase() {

    abstract fun getRouteInfoItemDao() : RoutInfoItemDao
    abstract fun getBusTimingPojoDao() : BusTimeEntityDao
    companion object{

        private var mInstance:BusRoutInfoDBHelper? = null

        fun getInstance(context:Context):BusRoutInfoDBHelper{
            if(mInstance == null) {
               mInstance = Room.databaseBuilder(
                    context.applicationContext, BusRoutInfoDBHelper::class.java, "BusRoutDB"
                ).build()
            }
            return mInstance as BusRoutInfoDBHelper
        }
    }
}