package com.kbomeisl.gukura.data.network
import android.util.Log
import com.kbomeisl.gukura.data.network.models.PlantNetwork
import com.kbomeisl.gukura.data.network.models.toUi
import com.kbomeisl.gukura.environmentalVariables
import com.kbomeisl.gukura.ui.models.PlantUi
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.request
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.gson.gson
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object plantNetworkDataSource {
    val logTag = "PlantDataSource"

    val ktorClient = HttpClient(CIO) {
        install(ContentNegotiation) {
            gson()
        }
    }

    private val requestRootUrl = environmentalVariables.firebaseRootUrl

    suspend fun getPlantList(): List<PlantNetwork> {
        Log.d(logTag,"get json")
        return ktorClient.get(requestRootUrl).body()
    }

    suspend fun getPlantsByNames(plantNameList: List<String>): List<PlantNetwork> {
        return getPlantList().filter {
            plantNameList.contains(it.name)
        }
    }
}



