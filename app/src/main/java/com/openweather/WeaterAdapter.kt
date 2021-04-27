package com.openweather

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.openweather.databinding.RowWeatherLayoutBinding
import java.text.ParseException
import java.text.SimpleDateFormat

class WeaterAdapter(
    val context: Context?, private val arrayList: ArrayList<WeatherViewModel>,val strCityName: String
) : RecyclerView.Adapter<WeaterAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val rowBinding: RowWeatherLayoutBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.row_weather_layout, parent, false)
        return ViewHolder(rowBinding)
    }

    override fun getItemCount(): Int = arrayList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(context, arrayList[position],strCityName)
    }

    class ViewHolder(private val weatherBinding: RowWeatherLayoutBinding) :
        RecyclerView.ViewHolder(weatherBinding.root) {
        @SuppressLint("SetTextI18n", "SimpleDateFormat")
        fun bindItem(
            context: Context?,
            viewModel: WeatherViewModel,
            strCityName: String
        ) {
            this.weatherBinding.data = viewModel
            //convert datetime formate to customize formate
            val formatter = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
            val strDate =  viewModel.weather.dt_txt
            try {
                val date = formatter.parse(strDate!!)
                val sdf1 = SimpleDateFormat("EEE, d MMMM hh:mm a")
                println(sdf1.format(date!!))
                weatherBinding.dateTimeText.text = sdf1.format(date)
            }catch (e: ParseException) {
                System.out.println("ParseException occured: " + e.localizedMessage)
                weatherBinding.dateTimeText.text = viewModel.weather.dt_txt
            }
            weatherBinding.textViewTemp.text = viewModel.weather.main.temp.toString()
            weatherBinding.textCloudType.text = viewModel.weather.weather[0].main
            weatherBinding.txtCityName.text = strCityName
            if(viewModel.weather.weather[0].main!!.contains("Cloud")||viewModel.weather.weather[0].main!!.contains("cloud")){
                weatherBinding.imageViewCloud.setBackgroundResource(R.drawable.cloudy_cloudy)
            }else if(viewModel.weather.weather[0].main!!.contains("Rain")){
                weatherBinding.imageViewCloud.setBackgroundResource(R.drawable.rain_cloud)
            }else if(viewModel.weather.weather[0].main!!.contains("Partly")){
                weatherBinding.imageViewCloud.setBackgroundResource(R.drawable.partly_cloud)
            }else if(viewModel.weather.weather[0].main!!.contains("Sun")){
                weatherBinding.imageViewCloud.setBackgroundResource(R.drawable.sun_weather)
            }else{
                weatherBinding.imageViewCloud.setBackgroundResource(R.drawable.partly_cloud)
            }


            weatherBinding.executePendingBindings()

        }
    }
}