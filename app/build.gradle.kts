plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.example.votingapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.votingapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

//    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
//    implementation(libs.firebase.crashlytics.buildtools)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation (libs.androidx.material.icons.extended)
    implementation (libs.gson)
//    implementation ("androidx.compose.foundation:foundation:1.7.5" )// Update as per latest version
//    implementation (libs.material3)



//    implementation(libs.androidx.core.ktx)
//    implementation(libs.androidx.appcompat)
//    implementation(libs.material)
//    implementation(libs.ui)
//    implementation(libs.androidx.material)
//    implementation(libs.ui.tooling.preview)
//    implementation(libs.androidx.activity.compose.v172)
//    implementation(libs.androidx.lifecycle.runtime.ktx.v261)
//    implementation(libs.androidx.foundation)
//    implementation(libs.androidx.runtime.livedata)
//    implementation(libs.androidx.foundation.layout)
//    implementation(libs.material3)
//    implementation(libs.androidx.activity.compose.v172)
//    implementation(libs.androidx.lifecycle.viewmodel.compose)
//    implementation(libs.androidx.navigation.compose)
//    implementation(libs.ui.tooling)
//    debugImplementation(libs.ui.test.manifest)
//    testImplementation(libs.junit)
//    androidTestImplementation(libs.androidx.junit.v115)
//    androidTestImplementation(libs.androidx.espresso.core.v351)
//    androidTestImplementation(libs.ui.test.junit4)
//    implementation (libs.gson)
//    implementation (libs.core)
//    implementation("org.web3j:core:4.12.0")
}