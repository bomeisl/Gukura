plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose") version "2.0.0"
    id("com.google.gms.google-services")
    id("com.google.devtools.ksp") version "2.0.21-1.0.25"
}

android {
    namespace = "com.kbomeisl.gukura"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.kbomeisl.gukura"
        minSdk = 24
        targetSdk = 34
        versionCode = 2
        versionName = "1.0.1-alpha"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    testImplementation(libs.androidx.junit)
    //Compose
    implementation(platform(libs.androidx.compose.bom.v20241100))
    androidTestImplementation(platform(libs.compose.bom.v20241000))
    implementation(libs.androidx.compose.material3.material3)
    androidTestImplementation(libs.ui.test.junit4)
    debugImplementation(libs.ui.test.manifest)
    implementation(libs.androidx.activity.compose.v192)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    debugImplementation(libs.androidx.ui.tooling)
    //Compose-Navigation
    implementation(libs.androidx.navigation.compose.v284)
    //Room
    val room_version = "2.6.1"
    implementation(libs.androidx.room.runtime)
    annotationProcessor(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)
    testImplementation(libs.androidx.room.testing)
    //Koin
    val koin_version = "4.0.0"
    implementation(project.dependencies.platform("io.insert-koin:koin-bom:$koin_version"))
    implementation(libs.koin.core)
    implementation(libs.koin.android)
    testImplementation(libs.koin.test.junit5)
    testImplementation(libs.koin.android.test)
    //Koin Jetpack Compose
    implementation(libs.koin.androidx.compose)
    implementation(libs.koin.androidx.compose.navigation)
    //Koin for Ktor
    implementation(libs.koin.ktor)
    implementation(libs.koin.logger.slf4j)
    //Ktor
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.cio)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.ktor.client.content.negotiation)
    //Firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
    //COIL
    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)
    //Unit Testing
    testImplementation(libs.junit)
    testImplementation(libs.androidx.core)
    androidTestImplementation (libs.core.ktx)
    testImplementation(libs.androidx.runner)
    testImplementation(libs.mockito.core)
    testImplementation(libs.mockito.kotlin)
    testImplementation(libs.mockk)
    testImplementation(libs.robolectric.v413)
    //Gson
    implementation(libs.gson)
    //Kotlin Serialization
    implementation(libs.ktor.serialization.gson)
    testImplementation(libs.kotlinx.coroutines.test)
    //Play
    implementation(libs.play.services.location)
}

