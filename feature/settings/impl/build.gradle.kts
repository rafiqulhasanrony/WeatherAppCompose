plugins {
    alias(libs.plugins.weatherapp.android.feature.impl)
    alias(libs.plugins.weatherapp.android.library.compose)
}

android {
    namespace = "com.example.weatherapp.feature.settings.impl"

}

dependencies {
    implementation(projects.feature.settings.publicApi)
}
