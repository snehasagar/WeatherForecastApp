package com.openweather

object weather {

    data class JSON(
        val cod: String?,
        val list: MutableList<listData>,
        val message:Int?,
        val city: cityData
    )
    {
        class cityData(
            val id: Int?,
            val name: String?,
            val country: String?
        )

        class listData(
            val dt_txt : String?,
            val weather: MutableList<weatherData>,
            val main:mainData,
            val clouds: cloudsData,
            val wind: windData

        ){
            data class weatherData(
                val id: Int?,
                val main: String?,
                val icon: String?,
                val description: String?
            )

            data class mainData(
                val feels_like: Double?,
                val humidity: Double?,
                val temp: Double?,
                val temp_max: Double?,
                val temp_min: Double?
            )

            data class cloudsData(
                val all: Int?
            )

            data class windData(
                val deg: Int?,
                val speed: Double?

            )
        }
    }

}
      /*  val id: Int?,
        val name: String?,
        val weather: MutableList<weatherData>,
        val main:mainData,
        val clouds: cloudsData,
        val wind: windData
    ) {
        data class weatherData(
            val id: Int?,
            val main: String?,
            val icon: String?,
            val description: String?
        )

        data class mainData(
            val feels_like: Double?,
            val humidity: Double?,
            val temp: Double?,
            val temp_max: Double?,
            val temp_min: Double?
        )

        data class cloudsData(
            val all: Int?
        )

        data class windData(
            val deg: Int?,
            val speed: Double?

        )
    }
}*/


