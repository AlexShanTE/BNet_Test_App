package com.alex.sid.shante.bnet.di

import com.alex.sid.shante.bnet.BuildConfig
import com.alex.sid.shante.bnet.data.BNetApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @get:Provides
    val baseUrl = BuildConfig.BASE_URL

    @Provides
    @Singleton
    fun provideRetrofitBuilder(baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideGitApi(retrofit: Retrofit): BNetApi {
        return retrofit.create(BNetApi::class.java)
    }
}