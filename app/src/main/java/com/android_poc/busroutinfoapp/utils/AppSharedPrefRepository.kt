package com.android_poc.busroutinfoapp.utils

import android.content.Context
import android.content.SharedPreferences
import com.android_poc.busroutinfoapp.BusRouteAndTimeApplication

class AppSharedPrefRepository private constructor(){

    private var sharedPrefrence:SharedPreferences? = null

    fun initPrefrence(context: Context){
        sharedPrefrence = context.getSharedPreferences(
            BusRouteAndTimeApplication::class.java.simpleName,Context.MODE_PRIVATE)
    }

    fun getBoolean(key:String,value:Boolean):Boolean{
        return sharedPrefrence?.getBoolean(key,true) ?: value
    }
    fun setBoolean(key:String,value: Boolean){
        val editor = sharedPrefrence!!.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    private object HOLDER{
        val INSTANCE = AppSharedPrefRepository()
    }

    companion object{
        fun getInstance() : AppSharedPrefRepository{
            val instance: AppSharedPrefRepository by lazy{HOLDER.INSTANCE}
            return instance
        }
    }

}