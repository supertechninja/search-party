package com.mcwilliams.searchparty.di

import com.mcwilliams.searchparty.network.QuoDbApi
import com.mcwilliams.searchparty.ui.QuoDbRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module(includes = [NetworkModule::class])
class AppModule {
    @Provides
    @Singleton
    fun providesQuoDbRepository(
        quoDbApi: QuoDbApi,
    ): QuoDbRepository =
        QuoDbRepository(quoDbApi)
}