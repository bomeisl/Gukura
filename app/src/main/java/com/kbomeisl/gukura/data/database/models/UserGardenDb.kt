package com.kbomeisl.gukura.data.database.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation

@Entity
data class UserGardenDb(
    @Embedded val gardenDb: GardenDb,
    @Relation(
        parentColumn = "gardenId",
        entityColumn = "plantId"
    )
    val plantDb: List<PlantDb>
)
