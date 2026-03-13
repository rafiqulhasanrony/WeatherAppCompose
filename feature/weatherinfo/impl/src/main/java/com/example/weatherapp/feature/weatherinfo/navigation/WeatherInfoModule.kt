package com.example.weatherapp.feature.weatherinfo.navigation

import com.example.baseapp.core.navigation.EntryProviderInstaller
import com.example.weatherApp.feature.settings.publicApi.navigation.SettingsNavKey
import com.example.weatherapp.feature.weatherinfo.WeatherInfoScreen
import com.example.weatherapp.weatherinfo.publicapi.navigation.WeatherInfoNavKey
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(SingletonComponent::class)
object WeatherInfoModule {

    @Provides
    @IntoSet
    fun provideSettingEntry(): EntryProviderInstaller = { navigator ->
        entry<WeatherInfoNavKey> {
            WeatherInfoScreen(
                onSearchClick = { },
                onSettingsClick = { navigator.navigateTo(SettingsNavKey) },
            )
        }
    }
}
