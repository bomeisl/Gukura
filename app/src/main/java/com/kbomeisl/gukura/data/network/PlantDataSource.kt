package com.kbomeisl.gukura.data.network
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.request
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpMethod
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json


class PlantDataSource() {
    val ktorClient = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json {
                isLenient = true
            })
        }
    }

    val requestRootUrl = ""

    suspend fun getPlantList() {
        val response: HttpResponse = ktorClient.request(requestRootUrl+"/plants") {
            method = HttpMethod.Get
        }
    }

    suspend fun getPlant(name: String) {
        val response: HttpResponse = ktorClient.request(requestRootUrl+"/plant"+name) {
            method = HttpMethod.Get
        }
    }

}



