@file:Suppress("DEPRECATION")

package com.openweather

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.launch
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import org.jetbrains.anko.sdk27.coroutines.onClick
import retrofit2.await

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    private lateinit var weatherViewModel: WeatherViewModel
    private lateinit var weaterAdapter: WeaterAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        weatherViewModel = ViewModelProviders.of(this).get(WeatherViewModel::class.java)

        rv_weather.visibility = View.GONE
        button.onClick {
            val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager?
            imm?.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)
            val cityName = editTextCityName.text.toString()

           // Loading.showLoading(this@MainActivity)
            launch {
                try {
                    val data = weatherViewModel.getWeatherReportFromAPI(cityName).await()
                    Log.d("Response--->",data.toString())

                    rv_weather.visibility = View.VISIBLE
                    weatherViewModel.getWeatherData(data.list).observe(this@MainActivity,
                        Observer {
                            val layoutM = LinearLayoutManager(this@MainActivity)
                            layoutM.orientation = LinearLayoutManager.VERTICAL
                            rv_weather.apply {
                                layoutManager = layoutM
                                weaterAdapter = WeaterAdapter(context,it,data.city.name!!)
                            }
                            rv_weather.adapter = weaterAdapter
                            weaterAdapter.notifyDataSetChanged()
                        })
                    //  Loading.hideLoading()

                } catch(e: Exception) {
                    e.printStackTrace()
                    Log.d("Response--->","failed")
                   // Loading.hideLoading()
                    Toast.makeText(this@MainActivity,"City Not found",Toast.LENGTH_LONG).show()
                    rv_weather.visibility = View.GONE


                }

            }
        }


    }
}