package com.kbomeisl.gukura.data.network.models


data class CurrentWeatherNetwork(
    val location: Location,
    val current: Current
)

data class Current(
    val last_updated: String,
    val last_updated_epoch: Int,
    val temp_c: Float,
    val temp_f: Float,
    val feelslike_c: Float,
    val feelslike_f: Float,
    val windchilld_c: Float,
    val windchill_f: Float,
    val heatindex_c: Float,
    val heatindex_f: Float,
    val dewpoint_c: Float,
    val dewpoint_f: Float,
    val condition_text: String,
    val condition_code: Int,
    val wind_mph: Float,
    val wind_kph: Float,
    val wind_degree: Int,
    val wind_dir: String,
    val pressure_mb: Float,
    val pressure_in: Float,
    val humidity: Int,
    val cloud: Int,
    val is_day: Int,
    val uv: Float,
    val gust_mph: Float,
    val gust_kph: Float
)

data class Location(
    var name           : String? = null,
    var region         : String? = null,
    var country        : String? = null,
    var lat            : Double? = null,
    var lon            : Double? = null,
    var tzId           : String? = null,
    var localtimeEpoch : Int?    = null,
    var localtime      : String? = null
)