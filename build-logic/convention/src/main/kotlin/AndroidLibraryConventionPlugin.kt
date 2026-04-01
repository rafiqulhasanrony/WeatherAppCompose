import com.android.build.api.dsl.LibraryExtension
import com.example.weatherapp.configureKotlinAndroid
import com.example.weatherapp.configureSpotlessForAndroid
import com.example.weatherapp.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "com.android.library")
            apply(plugin = "de.mannodermaus.android-junit5")

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
                testOptions.targetSdk = 35
                lint.targetSdk = 35
                defaultConfig.testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                testOptions.animationsDisabled = true
                // The resource prefix is derived from the module name,
                // so resources inside ":core:module1" must be prefixed with "core_module1_"
                resourcePrefix =
                    path.split("""\W""".toRegex()).drop(1).distinct().joinToString(separator = "_")
                        .lowercase() + "_"
            }
            configureSpotlessForAndroid()
            dependencies {
                if (path != ":core:testing") {
                    "implementation"(project(":core:testing"))
                }
                "testImplementation"(libs.findLibrary("kotlin.test").get())
                // "testImplementation"(libs.findLibrary("junit").get())
                "testImplementation"(libs.findLibrary("junit5").get())
                "testRuntimeOnly"(libs.findLibrary("junit5.jupiter.engine").get())
                "testImplementation"(libs.findLibrary("junit5.params").get())
                "testImplementation"(libs.findLibrary("mockk").get())

                "testImplementation"(libs.findLibrary("turbine").get())
                "androidTestImplementation"(libs.findLibrary("kotlin.test").get())
            }
        }
    }
}
