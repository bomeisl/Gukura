package com.kbomeisl.gukura

import com.kbomeisl.gukura.data.database.PlantDao
import com.kbomeisl.gukura.data.network.models.PlantNetwork
import com.kbomeisl.gukura.data.network.models.toDb
import com.kbomeisl.gukura.di.databaseModule
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.test.KoinTest
import org.koin.test.inject

class PlantModelTests: KoinTest {
    private lateinit var plantNetwork: PlantNetwork
    private val plantDao: PlantDao by inject()
    //private val context: Context = GukuraApplication().applicationContext

    @Before
    fun setUp() {
        startKoin {
            androidLogger()
            //androidContext(androidContext = context)
            modules(databaseModule)
        }

        plantNetwork = PlantNetwork(
            name = "TestName",
            species = "TestSpecies",
            tempMin = 0,
            tempMax = 50,
            humidityMin = 0,
            humidityMax = 50,
            lightMin = 0.0,
            lightMax = 50.0,
            directSunMax = 5,
            link = "www.example.com",
            summary = "summary",
            notes = "notes",
            image = "www.link.com"
        )
    }


    @Test
    fun plantDbModelShouldInsertToDb() {
        val plantDb = plantNetwork.toDb()
        runTest {
            plantDao.insertPlant(plantDb = plantDb)
        }
        val retrievedPlant = plantDao.findPlant(plantNetwork.name)
        Assert.assertSame(retrievedPlant,plantDb)
    }
}