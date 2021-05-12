package com.mcwilliams.sunshine.di

import com.mcwilliams.sunshine.network.QuoDbApi
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Module which provides all required dependencies about network
 */
@InstallIn(SingletonComponent::class)
@Module
@Suppress("unused")
object NetworkModule {
    /**
     * Provides the Post service implementation.
     * @param retrofit the Retrofit object used to instantiate the service
     * @return the Post service implementation.
     */
    @Provides
    @Reusable
    @JvmStatic
    internal fun provideQuoDbApi(retrofit: Retrofit): QuoDbApi {
        return retrofit.create(QuoDbApi::class.java)
    }


    @Provides
    @Reusable
    @JvmStatic
    internal fun provideQuoDbClientApi(
        okHttpClient: OkHttpClient.Builder
    ): Retrofit {

        return Retrofit.Builder()
            .baseUrl("http://api.quodb.com")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient.build())
            .build()
    }

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideOkHttp(): OkHttpClient.Builder {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()
        okHttpClient.addInterceptor(logging)
        return okHttpClient
    }
}