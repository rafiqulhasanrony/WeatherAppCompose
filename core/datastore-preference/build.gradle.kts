plugins {
    alias(libs.plugins.weatherapp.android.library)
    alias(libs.plugins.weatherapp.hilt)
}

android {
    namespace = "com.example.baseapp.core.datastore"

}

dependencies {
    api(libs.androidx.dataStore.preferences)
}
