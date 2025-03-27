package com.ragnorak.seatcodetest.input.di

import com.ragnorak.seatcodetest.input.data.datasource.LocalRoverMovementDataSource
import com.ragnorak.seatcodetest.persistence.dao.RoverMovementsDao
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object InputRoverMovementDataSourceModule {

    fun provideLocalRoverMovementDataSource(
        roverMovementsDao: RoverMovementsDao
    ): LocalRoverMovementDataSource {
        return LocalRoverMovementDataSource(roverMovementsDao)
    }
}