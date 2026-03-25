package com.example.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.baseapp.core.navigation.EntryProviderInstaller
import com.example.weatherapp.core.designsystem.component.AppBackground
import com.example.weatherapp.core.designsystem.theme.AppTheme
import com.example.weatherapp.navigation.WeatherAppNavDisplay
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var entryProviderInstaller: Set<@JvmSuppressWildcards EntryProviderInstaller>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val splashScreen = installSplashScreen()
        enableEdgeToEdge()

        // Keep the splash screen on-screen until the UI state is loaded. This condition is
        // evaluated each time the app needs to be redrawn so it should be fast to avoid blocking
        // the UI.
        // splashScreen.setKeepOnScreenCondition { false }
        setContent {
            AppTheme {
                AppBackground {
                    WeatherAppNavDisplay(modifier = Modifier.fillMaxSize(), entryProviderInstaller)
                }
            }
        }
    }
}
