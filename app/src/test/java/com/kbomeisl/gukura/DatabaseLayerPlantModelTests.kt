package com.kbomeisl.gukura

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.kbomeisl.gukura.data.database.GukuraDatabase
import com.kbomeisl.gukura.data.database.PlantDao
import com.kbomeisl.gukura.data.database.models.PlantDb
import com.kbomeisl.gukura.data.network.models.PlantNetwork
import com.kbomeisl.gukura.data.network.models.toDb
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlinx.coroutines.withContext
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner


@RunWith(RobolectricTestRunner::class)
class DatabaseLayerPlantModelTests {
    private lateinit var plantNetwork: PlantNetwork
    private lateinit var plantDao: PlantDao
    private lateinit var gukuraDatabase: GukuraDatabase
    private lateinit var context: Context
    private lateinit var plantDb: PlantDb

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        val dispatcher = StandardTestDispatcher()
        Dispatchers.setMain(dispatcher)
        context = ApplicationProvider.getApplicationContext()
        gukuraDatabase = Room.inMemoryDatabaseBuilder(
            context, GukuraDatabase::class.java
        ).build()
        plantDao = gukuraDatabase.plantDao()

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

        plantDb = PlantDb(
            name = "Species Test",
            plantId = "Species Test".hashCode(),
            description = "This is a plant",
            minTemperature = 10,
            maxTemperature = 54,
            minHumidity = 5,
            maxHumidity = 55,
            minLightLevel = 2300,
            maxLightLevel = 4567,
            imageUrl = "www.plants.com",
            wishListed = false,
            gardenName = "garden 4",
            gardenTemp = "93",
            gardenHumidity = "39",
            gardenLightLevel = "2390"
        )
    }

    @Test
    fun plantDbModelShouldTransactWithDb() {
        val insertedPlant = plantDb
        runTest {
            withContext(Dispatchers.IO) {
                plantDao.insertPlant(plantDb = plantDb)
                val retrievedPlant = plantDao.findPlant(plantNetwork.name)
                Assert.assertSame(retrievedPlant, insertedPlant)
            }
        }
    }

    @Test
    fun plantNetworkModelShouldMapToDbModel() {
        val insertedPlant = plantNetwork.toDb()
        runTest {
            withContext(Dispatchers.IO) {
                plantDao.insertPlant(plantDb = plantDb)
                val retrievedPlant = plantDao.findPlant(plantNetwork.name)
                Assert.assertSame(retrievedPlant, insertedPlant)
            }
        }
    }

    @After
    fun cleanUp() {
        gukuraDatabase.close()
    }
}