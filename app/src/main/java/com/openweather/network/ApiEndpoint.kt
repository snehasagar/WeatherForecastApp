package com.openweather.network

import com.openweather.weather
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiEndpoint {

    @GET("forecast?")
    fun getWeatherReport(
        @Query("q") q:String,
        @Query("APPID") APPID:String
    ): Call<weather.JSON>
}