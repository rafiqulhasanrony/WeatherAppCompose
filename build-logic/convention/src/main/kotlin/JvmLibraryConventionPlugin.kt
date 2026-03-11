import com.example.weatherapp.configureKotlinJvm
import com.example.weatherapp.configureSpotlessForJvm
import com.example.weatherapp.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies

class JvmLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "org.jetbrains.kotlin.jvm")
            apply(plugin = "weatherapp.android.lint")

            configureKotlinJvm()
            configureSpotlessForJvm()
            dependencies {
                "testImplementation"(libs.findLibrary("kotlin.test").get())
            }
        }
    }
}
