plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose") version "2.0.0"
    id("com.google.gms.google-services")
}

android {
    namespace = "com.kbomeisl.gukura"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.kbomeisl.gukura"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
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
    //Compose
    val composeBom = platform("androidx.compose:compose-bom:2024.09.03")
    implementation(composeBom)
    androidTestImplementation(composeBom)

    implementation(libs.material3)
    androidTestImplementation(libs.ui.test.junit4)
    debugImplementation(libs.ui.test.manifest)
    implementation(libs.androidx.activity.compose.v192)
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.6")
    debugImplementation(libs.androidx.ui.tooling)
    //Compose Sensors 3rd party
    implementation(libs.composesensors)
    //Compose-Navigation
    implementation("androidx.navigation:navigation-compose:2.8.3")
    //Room
    val room_version = "2.6.1"
    implementation("androidx.room:room-runtime:$room_version")
    annotationProcessor("androidx.room:room-compiler:$room_version")
    //Koin
    implementation("io.insert-koin:koin-android:4.0.0")
    //Koin Jetpack Compose
    implementation("io.insert-koin:koin-androidx-compose:4.0.0")
    implementation("io.insert-koin:koin-androidx-compose-navigation:4.0.0")
    //Ktor
    val ktorVersion = "3.0.0"
    implementation("io.ktor:ktor-client-core:$ktorVersion")
    implementation("io.ktor:ktor-client-cio:$ktorVersion")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
    implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
    //Firebase
    implementation(platform("com.google.firebase:firebase-bom:33.4.0"))
    implementation("com.google.firebase:firebase-analytics")
}
