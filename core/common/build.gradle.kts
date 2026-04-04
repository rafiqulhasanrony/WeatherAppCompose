plugins {
    alias(libs.plugins.weatherapp.jvm.library)
    alias(libs.plugins.weatherapp.hilt)
}

dependencies {
    implementation(libs.kotlinx.coroutines.core)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.turbine)
}
