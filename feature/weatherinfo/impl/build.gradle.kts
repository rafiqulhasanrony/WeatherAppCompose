plugins {
    alias(libs.plugins.weatherapp.android.feature.impl)
    alias(libs.plugins.weatherapp.android.library.compose)
}

android {
    namespace = "com.example.weatherapp.feature.weatherinfo"
    testOptions.unitTests.isIncludeAndroidResources = true
}

dependencies {
    implementation(projects.feature.weatherinfo.publicApi)
    implementation(projects.feature.settings.publicApi)
}
