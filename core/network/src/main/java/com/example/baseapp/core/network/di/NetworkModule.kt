package com.example.baseapp.core.network.di

import android.content.Context
import com.example.baseapp.core.network.ForceCacheInterceptor
import com.example.weatherapp.core.network.BuildConfig
import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.internal.platform.Platform
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {
    private const val REQUEST_TIMEOUT = 30L
    private const val CACHE_SIZE: Long = 10L * 1024L * 1024L // 10 MB

    /**
     * The method returns the Moshi object
     **/

    @Provides
    @Singleton
    fun providesNetworkJson(): Json = Json {
        ignoreUnknownKeys = true
    }

    @Provides
    @Singleton
    fun provideOkhttpClient(@ApplicationContext context: Context): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(getLogInterceptors(BuildConfig.DEBUG))
            .addInterceptor(ForceCacheInterceptor(context))
            .cache(getCache(context)).build()
    }

    @Provides
    @Singleton
    internal fun provideRetrofit(okHttpClient: OkHttpClient, json: Json): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BACKEND_URL)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .client(okHttpClient)
            .build()
    }

    @Suppress("SameParameterValue")
    private fun getLogInterceptors(isDebugAble: Boolean = false): Interceptor {
        val builder = LoggingInterceptor.Builder()
            .setLevel(if (isDebugAble) Level.BASIC else Level.NONE)
            .log(Platform.INFO)
            .tag("WeatherInfo")
            .request("Request")
            .response("Response")
        builder.isDebugAble = isDebugAble
        return builder.build()
    }

    private fun getCache(context: Context) = Cache(context.cacheDir, CACHE_SIZE)
}
