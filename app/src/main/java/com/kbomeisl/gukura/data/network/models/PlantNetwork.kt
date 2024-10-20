package com.kbomeisl.gukura.data.network.models

import com.google.gson.annotations.SerializedName
import com.kbomeisl.gukura.data.database.models.PlantDb
import com.kbomeisl.gukura.ui.models.PlantUi
import kotlinx.serialization.Serializable


data class PlantNetwork (

    @SerializedName("name"         ) var name         : String,
    @SerializedName("species"      ) var species      : String,
    @SerializedName("tempMin"      ) var tempMin      : Int,
    @SerializedName("tempMax"      ) var tempMax      : Int,
    @SerializedName("lightMin"     ) var lightMin     : Double,
    @SerializedName("lightMax"     ) var lightMax     : Double,
    @SerializedName("directSunMax" ) var directSunMax : Int,
    @SerializedName("humidityMin"  ) var humidityMin  : Int,
    @SerializedName("humidityMax"  ) var humidityMax  : Int,
    @SerializedName("link"         ) var link         : String,
    @SerializedName("summary"      ) var summary      : String,
    @SerializedName("notes"        ) var notes        : String,
    @SerializedName("image"        ) var image        : String

)
fun PlantNetwork.toUi(): PlantUi =
    PlantUi(
        name = name,
        description = summary,
        temperature = "${tempMin}-${tempMax}",
        humidity = "${humidityMin}-${humidityMax}",
        lightLevel = "${lightMin}-${lightMax}",
        imageUrl = image
    )


fun PlantNetwork.toDb(): PlantDb =
    PlantDb(
        name = name,
        description = summary,
        maxTemperature = tempMin,
        minTemperature = tempMax,
        minHumidity = humidityMin,
        maxHumidity = humidityMax,
        minLightLevel = lightMin.toInt(),
        maxLightLevel = lightMax.toInt(),
        imageUrl = image
    )



