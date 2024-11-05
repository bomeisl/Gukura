package com.kbomeisl.gukura.data.network

import com.kbomeisl.gukura.data.network.models.CurrentWeatherNetwork
import com.kbomeisl.gukura.data.network.models.PlantNetwork
import com.kbomeisl.gukura.environmentalVariables
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.gson.gson

object weatherNetworkDataSource {
    private val rootUrl = "http://api.weatherapi.com/v1/current.json?key=${environmentalVariables.weatherApiKey}&q=NYC"

    val ktorClient = HttpClient(CIO) {
        install(ContentNegotiation) {
            gson()
        }
    }

    suspend fun getWeather(): CurrentWeatherNetwork {
        return ktorClient.get(rootUrl).body()
    }
}