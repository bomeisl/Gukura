package com.kbomeisl.gukura

import android.content.Context
import com.kbomeisl.gukura.data.database.models.PlantDb
import com.kbomeisl.gukura.data.repository.PlantRepository
import com.kbomeisl.gukura.di.databaseModule
import com.kbomeisl.gukura.di.repositoryModule
import com.kbomeisl.gukura.ui.testData.plants
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.java.KoinJavaComponent.get
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PlantRespositoryUnitTests {
    private lateinit var testPlant: PlantDb
    private lateinit var plantRepository: PlantRepository
    private lateinit var context: Context

    @Before
    fun setUp() {
        context = Mockito.mock(Context::class.java)
        startKoin {
            modules(databaseModule, repositoryModule)
            androidContext(context)
        }
        testPlant = plants.plantList.first()
        plantRepository = get(PlantRepository::class.java)
    }

    @Test
    fun shouldInsertPlantIntoDatabase() {
        runTest {
            plantRepository.upsertPlant(testPlant)
            val plant = plantRepository.getPlantDb(testPlant.name)
            Assert.assertSame(plant, testPlant)
        }
    }
}