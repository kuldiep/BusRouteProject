package com.android_poc.busroutinfoapp.utils

import com.android_poc.busroutinfoapp.BusRouteAndTimeApplication
import org.json.JSONObject
import java.io.InputStream
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

object AppUtils {

    fun loadJsonFileFromAsset(fileName: String): JSONObject {
        var json: String = ""
        try {
            val inputStream: InputStream =
                BusRouteAndTimeApplication.getApplicationInstance().assets.open(fileName)
            json = inputStream.bufferedReader().use { it.readText() }

        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return JSONObject(json)
    }


    fun getMiliSecondsFromString(timeInString: String): Int {

      val listOfToken= timeInString.split(":")
        val minutesToMs: Int = listOfToken.get(1).toInt() * 60000
        val hoursToMs: Int = listOfToken.get(0).toInt() * 3600000
        val total: Int =  (minutesToMs + hoursToMs)
        return total
    }

    fun getTimeAndDate(value: Long?, dateFormate: String?): String? {
        val sdf = SimpleDateFormat(dateFormate)
        return sdf.format(Date(value!!))
    }

}