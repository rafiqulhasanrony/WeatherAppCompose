plugins {
    alias(libs.plugins.weatherapp.android.feature)
    alias(libs.plugins.weatherapp.android.library.compose)
}

android {
    namespace = "com.example.weatherapp.feature.weatherinfo"
    testOptions.unitTests.isIncludeAndroidResources = true
}

dependencies {

}