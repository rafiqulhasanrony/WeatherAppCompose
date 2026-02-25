package com.example.baseapp.core.datastore.di

import com.example.baseapp.core.datastore.PreferenceDataStoreAPI
import com.example.baseapp.core.datastore.PreferenceDataStoreAPIImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created By Rafiqul Hasan
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class DataStoreApiModule {

    @Binds
    @Singleton
    abstract fun providePreferenceDataStoreAPI(sourceImpl: PreferenceDataStoreAPIImpl): PreferenceDataStoreAPI
}
