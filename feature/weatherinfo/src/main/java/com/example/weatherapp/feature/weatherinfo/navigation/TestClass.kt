package com.example.weatherapp.feature.weatherinfo.navigation

import android.content.Context
import androidx.annotation.RawRes
import com.example.weatherapp.feature.weatherinfo.R

class TestClass(val context: Context) {

    fun response() {
        readJsonFromRaw(context, R.raw.weather_info)
    }

    fun readJsonFromRaw(context: Context, @RawRes resourceId: Int): String {
        return context.resources.openRawResource(resourceId)
            .bufferedReader()
            .use { it.readText() }
    }

//    private fun getWeatherInfoTResponse(jsonString: String): Resource<WeatherInfoResponse> {
//        val moshi = Moshi.Builder()
//            .build()
//        val jsonAdapter: JsonAdapter<WeatherInfoResponse> = moshi.adapter(WeatherInfoResponse::class.java)
//        return Resource.Success(jsonAdapter.fromJson(jsonString)!!)
//    }
}
