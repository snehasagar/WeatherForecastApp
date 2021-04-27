package com.openweather

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.GsonBuilder
import com.openweather.network.ApiEndpoint
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class WeatherViewModel : ViewModel{


    companion object {
        var BaseUrl = "https://api.openweathermap.org/data/2.5/"
        var ApiKey = "793f30d0b9d96bb0cd1c227409485a82"
    }

    val gson = GsonBuilder()
        .setLenient()
        .create()

    val retrofit = Retrofit.Builder()
        .baseUrl(BaseUrl)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
    val service = retrofit.create(ApiEndpoint::class.java)


    fun getWeatherReportFromAPI(cityname: String): Call<weather.JSON> {
        return service.getWeatherReport(cityname, ApiKey)
    }

    private var _city = MutableLiveData<String>()
    var city: MutableLiveData<String>
        get() = _city
        set(value) {
            _city = value
        }

    lateinit var weather: weather.JSON.listData
    private var weatherLiveData = MutableLiveData<ArrayList<WeatherViewModel>>()
    private var arrayListWeather = ArrayList<WeatherViewModel>()

    constructor(weather: weather.JSON.listData) : super() {
        this.weather = weather
    }
    fun getWeatherData(data: MutableList<weather.JSON.listData>): MutableLiveData<ArrayList<WeatherViewModel>> {
        arrayListWeather.clear()
        for (i in 0 until data.size) {
            arrayListWeather.add(WeatherViewModel(
                com.openweather.weather.JSON.listData(
                data[i].dt_txt,
                data[i].weather,
                data[i].main,
                data[i].clouds,
                data[i].wind)))
        }
        weatherLiveData.value = arrayListWeather
        return weatherLiveData
    }

}