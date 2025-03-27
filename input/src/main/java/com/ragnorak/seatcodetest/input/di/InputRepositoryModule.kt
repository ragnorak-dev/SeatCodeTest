package com.ragnorak.seatcodetest.input.di

import com.ragnorak.seatcodetest.input.data.repository.InputRoverMovementRepositoryImpl
import com.ragnorak.seatcodetest.input.domain.repository.InputRoverMovementRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class InputRoverMovementRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindInputRoverMovementRepository(impl: InputRoverMovementRepositoryImpl): InputRoverMovementRepository

}