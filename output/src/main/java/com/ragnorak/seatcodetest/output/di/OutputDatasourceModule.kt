package com.ragnorak.seatcodetest.output.di

import com.ragnorak.seatcodetest.output.data.datasource.OutputRoverMovementLocalDataSource
import com.ragnorak.seatcodetest.persistence.dao.RoverMovementsDao
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object OutputDataSourceModule {

    fun provideOutputRoverMovementDataSource(
        roverMovementsDao: RoverMovementsDao
    ): OutputRoverMovementLocalDataSource {
        return OutputRoverMovementLocalDataSource(roverMovementsDao)
    }
}