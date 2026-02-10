import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
    alias(libs.plugins.android.lint)
}

group = "com.example.weatherapp.build_logic.convention"

// Configure the build-logic plugins to target JDK 17
// This matches the JDK used to build the project, and is not related to what is running on device.
java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_17
    }
}

dependencies {
    compileOnly(libs.android.gradleApiPlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.android.tools.common)
    compileOnly(libs.compose.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
    implementation(libs.truth)
    compileOnly(libs.spotless.gradlePlugin)

//    compileOnly(libs.firebase.crashlytics.gradlePlugin)
//    compileOnly(libs.firebase.performance.gradlePlugin)
//    compileOnly(libs.room.gradlePlugin)
//    lintChecks(libs.androidx.lint.gradle)
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}


gradlePlugin {
    plugins {
        register("androidApplicationCompose") {
            id = libs.plugins.weatherapp.android.application.compose.get().pluginId
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }
        register("androidApplication") {
            id = libs.plugins.weatherapp.android.application.asProvider().get().pluginId
            implementationClass = "AndroidApplicationConventionPlugin"
        }
//        register("androidApplicationJacoco") {
//            id = libs.plugins.nowinandroid.android.application.jacoco.get().pluginId
//            implementationClass = "AndroidApplicationJacocoConventionPlugin"
//        }
        register("androidLibraryCompose") {
            id = libs.plugins.weatherapp.android.library.compose.get().pluginId
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }
        register("androidLibrary") {
            id = libs.plugins.weatherapp.android.library.asProvider().get().pluginId
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidFeature") {
            id = libs.plugins.weatherapp.android.feature.get().pluginId
            implementationClass = "AndroidFeatureConventionPlugin"
        }
//        register("androidLibraryJacoco") {
//            id = libs.plugins.nowinandroid.android.library.jacoco.get().pluginId
//            implementationClass = "AndroidLibraryJacocoConventionPlugin"
//        }
//        register("androidTest") {
//            id = libs.plugins.nowinandroid.android.test.get().pluginId
//            implementationClass = "AndroidTestConventionPlugin"
//        }
        register("hilt") {
            id = libs.plugins.weatherapp.hilt.get().pluginId
            implementationClass = "HiltConventionPlugin"
        }
//        register("androidRoom") {
//            id = libs.plugins.nowinandroid.android.room.get().pluginId
//            implementationClass = "AndroidRoomConventionPlugin"
//        }
//        register("androidFirebase") {
//            id = libs.plugins.nowinandroid.android.application.firebase.get().pluginId
//            implementationClass = "AndroidApplicationFirebaseConventionPlugin"
//        }
//        register("androidFlavors") {
//            id = libs.plugins.nowinandroid.android.application.flavors.get().pluginId
//            implementationClass = "AndroidApplicationFlavorsConventionPlugin"
//        }
        register("androidLint") {
            id = libs.plugins.weatherapp.android.lint.get().pluginId
            implementationClass = "AndroidLintConventionPlugin"
        }
        register("jvmLibrary") {
            id = libs.plugins.weatherapp.jvm.library.get().pluginId
            implementationClass = "JvmLibraryConventionPlugin"
        }
        register("root") {
            id = libs.plugins.weatherapp.root.get().pluginId
            implementationClass = "RootPlugin"
        }
    }
}
