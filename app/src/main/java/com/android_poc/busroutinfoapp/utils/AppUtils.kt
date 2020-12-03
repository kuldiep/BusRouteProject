package com.android_poc.busroutinfoapp.utils

import com.android_poc.busroutinfoapp.BusRouteAndTimeApplication
import org.json.JSONObject
import java.io.InputStream

object AppUtils {

     fun loadJsonFileFromAsset(fileName:String): JSONObject {
        var json: String = ""
        try {
            val inputStream: InputStream = BusRouteAndTimeApplication.getApplicationInstance().
            assets.open(fileName)
            json = inputStream.bufferedReader().use { it.readText() }

        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return JSONObject(json)
    }
}