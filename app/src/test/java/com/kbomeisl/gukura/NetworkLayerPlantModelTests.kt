package com.kbomeisl.gukura

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.kbomeisl.gukura.data.database.GukuraDatabase
import com.kbomeisl.gukura.data.database.PlantDao
import com.kbomeisl.gukura.data.database.models.PlantDb
import com.kbomeisl.gukura.data.database.models.toUi
import com.kbomeisl.gukura.data.network.models.PlantNetwork
import com.kbomeisl.gukura.data.network.models.toDb
import com.kbomeisl.gukura.data.network.models.toUi
import com.kbomeisl.gukura.data.network.plantNetworkDataSource
import com.kbomeisl.gukura.data.repository.PlantRepository
import com.kbomeisl.gukura.di.databaseModule
import com.kbomeisl.gukura.di.repositoryModule
import com.kbomeisl.gukura.ui.models.PlantUi
import com.kbomeisl.gukura.ui.testData.plants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.withContext
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.java.KoinJavaComponent.get
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class NetworkLayerPlantModelTests {
    private lateinit var plantNetwork: PlantNetwork
    private lateinit var plantUi: PlantUi
    private lateinit var plantDb: PlantDb
    private lateinit var context: Context
    private lateinit var gukuraDatabase: GukuraDatabase
    private lateinit var plantDao: PlantDao

    @Before
    fun setUp() {
        context = ApplicationProvider.getApplicationContext()
        gukuraDatabase = Room.inMemoryDatabaseBuilder(
            context, GukuraDatabase::class.java
        ).build()
        plantDao = gukuraDatabase.plantDao()
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

        plantUi = PlantUi(
            name = "TestName",
            description = "Test description",
            temperature = "50",
            humidity = "50",
            lightLevel = "50.0",
            wishListed = true,
            gardenTemp = "Garden Temp",
            gardenName = "Garden Name",
            gardenHumidity = "Garden Humidity",
            gardenLightLevel = "Garden Light Level"
        )
    }

    @Test
    fun successfullyRetrievePlantFromNetwork() {
        val plantName = "Hoya"
        runTest {
            withContext(Dispatchers.IO) {
                plantNetwork = plantNetworkDataSource.getPlantsByNames(listOf(plantName)).first()
                Assert.assertSame(plantName, plantNetwork.name)
            }
        }
    }

    @Test
    fun networkLayerPlantShouldInsertToDb() {
        val plantName = "Hoya"
        runTest {
            withContext(Dispatchers.IO) {
                plantNetwork = plantNetworkDataSource.getPlantsByNames(listOf(plantName)).first()
                plantDao.upsertPlant(plantNetwork.toDb())
                val retrievedPlant = plantDao.findPlant("Hoya")
                Assert.assertSame(retrievedPlant, plantNetwork.toDb())
            }
        }
    }
}