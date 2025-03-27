package com.ragnorak.seatcodetest.input.di

import com.ragnorak.seatcodetest.input.data.datasource.LocalRoverMovementDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object InputRoverMovementDataSourceModule {

    @Provides
    @Singleton
    fun provideLocalRoverMovementDataSource(): LocalRoverMovementDataSource {
        return LocalRoverMovementDataSource()
    }
}