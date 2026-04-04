plugins {
    alias(libs.plugins.weatherapp.android.library)
    alias(libs.plugins.weatherapp.android.library.compose)
}

android {
    namespace = "com.example.weatherapp.core.designsystem"
    //testOptions.unitTests.isIncludeAndroidResources = true

    buildFeatures {
        buildConfig = true
    }

    buildTypes {
        debug {
        }
        release {
        }
    }
}

dependencies {
    api(libs.androidx.compose.foundation)
    api(libs.androidx.compose.foundation.layout)
    api(libs.androidx.compose.material.iconsExtended)
    api(libs.androidx.compose.material3)
    api(libs.androidx.compose.material3.adaptive)
    api(libs.androidx.compose.material3.navigationSuite)
    api(libs.androidx.compose.runtime)
    api(libs.androidx.compose.ui.util)
}
