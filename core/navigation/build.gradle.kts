plugins {
    alias(libs.plugins.weatherapp.android.library)
    alias(libs.plugins.weatherapp.android.library.compose)
}

android {
    namespace = "com.example.baseapp.core.navigation"
}

dependencies {
    api(libs.androidx.navigation3.runtime)
}
