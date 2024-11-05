package com.kbomeisl.gukura.data.repository

import com.kbomeisl.gukura.data.network.models.CurrentWeatherNetwork
import com.kbomeisl.gukura.data.network.weatherNetworkDataSource

class WeatherRepository {
    suspend fun getWeather(): CurrentWeatherNetwork {
       return weatherNetworkDataSource.getWeather()
    }
}