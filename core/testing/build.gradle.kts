plugins {
    alias(libs.plugins.weatherapp.android.library)
}

android {
    namespace = "com.example.baseapp.core.testing"
}

dependencies {
    api(libs.kotlinx.coroutines.test)

    implementation(libs.androidx.test.rules)
    implementation(libs.hilt.android.testing)
    implementation(libs.mockito.core)
    implementation(libs.mockito.kotlin)
    implementation(libs.mockito.android)
    implementation(libs.assertj.core)
    implementation(libs.kotlin.test)
    implementation(kotlin("test"))
}
