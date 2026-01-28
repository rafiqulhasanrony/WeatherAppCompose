import com.android.build.api.dsl.ApplicationExtension
import com.example.weatherapp.constant.ProjectConfig.TARGET_SDK_VERSION
import com.example.weatherapp.configureKotlinAndroid
import com.example.weatherapp.constant.ProjectConfig.APPLICATION_ID
import com.example.weatherapp.constant.ProjectConfig.NAME_SPACE
import com.example.weatherapp.constant.ProjectConfig.VERSION_CODE
import com.example.weatherapp.constant.ProjectConfig.VERSION_NAME
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "com.android.application")

            extensions.configure<ApplicationExtension> {
                configureKotlinAndroid(this)
                namespace = NAME_SPACE
                testOptions.animationsDisabled = true
                defaultConfig.targetSdk = TARGET_SDK_VERSION
                defaultConfig.applicationId = APPLICATION_ID
                defaultConfig.versionCode = VERSION_CODE
                defaultConfig.versionName = VERSION_NAME
            }
        }
    }
}
