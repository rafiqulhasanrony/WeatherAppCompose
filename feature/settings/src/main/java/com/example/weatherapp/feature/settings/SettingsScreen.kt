package com.example.weatherapp.feature.settings

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.weatherapp.core.ui.AppStandardToolbar

@Composable
internal fun SettingsRoute(onBackPress:()-> Unit){
    SettingsScreen(onBackPress)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SettingsScreen(
    onBackPress:()-> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.Transparent,
        contentColor = MaterialTheme.colorScheme.onBackground,
        topBar = {
            AppStandardToolbar(
                title = "Settings",
                onBackClick = onBackPress
            )
        }
    )
    { innerPadding ->
        Greeting(
            name = "Settings",
            modifier = Modifier.padding(innerPadding)
        )
    }
}
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}