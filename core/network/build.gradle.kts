import com.android.build.api.variant.BuildConfigField

plugins {
    alias(libs.plugins.weatherapp.android.library)
    alias(libs.plugins.weatherapp.hilt)
    id("kotlinx-serialization")
}

android {
    namespace = "com.example.weatherapp.core.network"

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    api(projects.core.common)

    implementation(libs.kotlinx.serialization.json)
    implementation(libs.http.logging){
        exclude(group = "org.json", module = "json")
    }
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.kotlin.serialization)

    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.turbine)
}

androidComponents {
    onVariants {
        it.buildConfigFields!!.put(
            "BACKEND_URL",
            BuildConfigField(
                type = "String",
                value = """"https://api.example.com"""",
                comment = null
            )
        )

        it.buildConfigFields!!.put(
            "API_KEY",
            BuildConfigField(
                type = "String",
                value = """"SECRET_KEY"""",
                comment = null
            )
        )
    }
}
