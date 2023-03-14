package com.alex.sid.shante.bnet.di

import com.alex.sid.shante.bnet.data.BNetApi
import com.alex.sid.shante.bnet.data.HomeRepositoryImpl
import com.alex.sid.shante.bnet.domain.repositories.HomeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideHomeRepository(
        bNetApi: BNetApi
    ): HomeRepository {
        return HomeRepositoryImpl(bNetApi)
    }

}