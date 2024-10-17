package com.kbomeisl.gukura.data.network
import android.util.Log
import com.kbomeisl.gukura.environmentalVariables
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.request
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json


class PlantNetworkDataSource() {
    val logTag = "PlantDataSource"

    val ktorClient = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json {
                isLenient = true
            })
        }
    }

    val requestRootUrl = environmentalVariables.firebaseRootUrl

    //always use a suspend function for network operations. They can take an arbitrary amount of time
    //and you will block the UI thread otherwise.
    suspend fun getPlantList(): List<PlantNetwork> {
        val response: HttpResponse = ktorClient.request(requestRootUrl+"/plantData"+"/plants") {
            method = HttpMethod.Get
        }
        var plantList = listOf<PlantNetwork>()

        if (response.status == HttpStatusCode.OK) {
            Log.i(logTag, "OK")
            plantList = response.body()
        } else {
            Log.e(logTag ,"${response.status}")
        }

        return plantList
    }

    suspend fun getPlant(name: String): PlantNetwork {
        val response: HttpResponse = ktorClient.request(requestRootUrl+"plantData"+"/plant"+name) {
            method = HttpMethod.Get
        }
        var plant: PlantNetwork = PlantNetwork()

        if (response.status == HttpStatusCode.OK) {
            Log.i(logTag, "OK")
            plant = response.body()
        } else {
            Log.e(logTag ,"${response.status}")
        }

        return plant
    }
}



