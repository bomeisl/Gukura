package com.kbomeisl.gukura.data.sensor

enum class CompassHeading(
    val dir: String,
    val min: Float,
    val max: Float
) {
    SOUTH("South",160f,200f),
    WEST("West",250f,290f),
    EAST("East",70f,110f),
    NORTH("North",340f,360f),
    NORTH2("North",0f,20f)
}