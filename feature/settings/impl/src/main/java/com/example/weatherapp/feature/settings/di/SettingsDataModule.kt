package com.example.weatherapp.feature.settings.di

import com.example.weatherapp.feature.settings.data.preference.SettingsPreferenceDataSourceImpl
import com.example.weatherapp.feature.settings.data.repository.SettingsRepositoryImpl
import com.example.weatherapp.feature.settings.domain.preference.SettingsPreferenceDataSource
import com.example.weatherapp.feature.settings.domain.repository.SettingsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class SettingsDataModule {

    @Singleton
    @Binds
    abstract fun bindSettingsRepository(settingRepositoryImpl: SettingsRepositoryImpl): SettingsRepository

    @Singleton
    @Binds
    abstract fun bindSettingsPreferenceDataStore(settingsPreferenceDataSource: SettingsPreferenceDataSourceImpl): SettingsPreferenceDataSource
}
