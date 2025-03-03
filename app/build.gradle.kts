plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    kotlin("kapt")
}

android {
    namespace = "io.lzaycoe.zynema"
    compileSdk = 35

    defaultConfig {
        applicationId = "io.lzaycoe.zynema"
        minSdk = 29
        //noinspection OldTargetApi
        targetSdk = 34
        versionCode = 1
        versionName = "1.0.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
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
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    debugImplementation(libs.androidx.ui.tooling)

    implementation("com.github.bumptech.glide:glide:4.16.0")
    kapt("com.github.bumptech.glide:compiler:4.16.0")

    runtimeOnly("androidx.room:room-runtime:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1")
    runtimeOnly("androidx.room:room-ktx:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1")

    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")

    runtimeOnly("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.7")
    runtimeOnly("androidx.lifecycle:lifecycle-livedata-ktx:2.8.7")

    runtimeOnly("io.coil-kt:coil:2.7.0")

    runtimeOnly("androidx.activity:activity-ktx:1.10.0")
    runtimeOnly("androidx.fragment:fragment-ktx:1.8.6")

    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

    runtimeOnly("androidx.paging:paging-runtime:3.3.6")

    runtimeOnly("com.squareup.moshi:moshi-kotlin:1.15.2")

    runtimeOnly("com.squareup.retrofit2:converter-moshi:2.11.0")

    implementation("com.github.smarteist:Android-Image-Slider:1.4.0")

    runtimeOnly("com.google.android.material:material:1.12.0")

    runtimeOnly("androidx.appcompat:appcompat:1.7.0")
}