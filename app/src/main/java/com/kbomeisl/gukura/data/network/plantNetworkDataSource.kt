package com.kbomeisl.gukura.data.network
import com.google.gson.GsonBuilder
import com.kbomeisl.gukura.data.network.models.PlantNetwork
import com.kbomeisl.gukura.environmentalVariables
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.gson.gson

object plantNetworkDataSource {
    val logTag = "PlantDataSource"

    val ktorClient = HttpClient(CIO) {
        install(ContentNegotiation) {
           gson{
               GsonBuilder().setPrettyPrinting().serializeNulls()
           }
        }
    }

    private val requestRootUrl = environmentalVariables.firebaseRootUrl

    suspend fun getPlantList(): List<PlantNetwork> {
        return ktorClient.get(requestRootUrl).body()
    }

    suspend fun getPlantsByNames(plantNameList: List<String>): List<PlantNetwork> {
        return getPlantList().filter {
            plantNameList.contains(it.name)
        }
    }
}



