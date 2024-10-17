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

    val requestRootUrl = "gs://rosemerta-6e458.appspot.com"

    //always use a suspend function for network operations. They can take an arbitrary amount of time
    //and you will block the UI thread otherwise.
    suspend fun getPlantList() {
        val response: HttpResponse = ktorClient.request(requestRootUrl+"/plantData"+"/plants") {
            method = HttpMethod.Get
        }
    }

    suspend fun getPlant(name: String) {
        val response: HttpResponse = ktorClient.request(requestRootUrl+"plantData"+"/plant"+name) {
            method = HttpMethod.Get
        }
    }

    suspend fun getPlantPicture(name: String) {
        val response: HttpResponse = ktorClient.request(requestRootUrl+"plantPics"+"/plant"+name) {
            method = HttpMethod.Get
        }
    }

}



