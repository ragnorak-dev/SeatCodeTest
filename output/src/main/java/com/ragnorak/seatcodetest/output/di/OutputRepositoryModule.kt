package com.ragnorak.seatcodetest.output.di

import com.ragnorak.seatcodetest.output.data.repository.OutputRoverMovementRepositoryImpl
import com.ragnorak.seatcodetest.output.domain.repository.OutputRoverMovementRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class OutputRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindOutputRoverMovementRepository(impl: OutputRoverMovementRepositoryImpl): OutputRoverMovementRepository

}