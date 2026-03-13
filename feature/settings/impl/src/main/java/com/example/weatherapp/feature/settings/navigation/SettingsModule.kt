package com.example.weatherapp.feature.settings.navigation

import com.example.baseapp.core.navigation.EntryProviderInstaller
import com.example.weatherApp.feature.settings.publicApi.navigation.SettingsNavKey
import com.example.weatherapp.feature.settings.SettingsScreen
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(SingletonComponent::class)
object SettingsModule {

    @Provides
    @IntoSet
    fun provideSettingEntry(): EntryProviderInstaller = { navigator ->
        entry<SettingsNavKey> {
            SettingsScreen(
                onBackPress = {
                    navigator.pop()
                },
            )
        }
    }
}
