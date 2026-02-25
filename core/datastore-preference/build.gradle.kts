plugins {
    alias(libs.plugins.weatherapp.android.library)
    alias(libs.plugins.weatherapp.hilt)
}

android {
    namespace = "com.example.baseapp.core.datastore"

}

dependencies {
    implementation(libs.androidx.dataStore.preferences)

}
