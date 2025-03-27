package com.ragnorak.seatcodetest.input.data.repository

import com.ragnorak.seatcodetest.input.data.datasource.LocalRoverMovementDataSource
import com.ragnorak.seatcodetest.input.data.mapper.toEntity
import com.ragnorak.seatcodetest.input.domain.repository.InputRoverMovementRepository
import com.ragnorak.seatcodetest.input.domain.model.RoverMovementModel
import javax.inject.Inject

class InputRoverMovementRepositoryImpl @Inject constructor (private val localRoverMovementDataSource: LocalRoverMovementDataSource) :
    InputRoverMovementRepository {
    override suspend fun sendMovement(movement: RoverMovementModel): Result<Boolean> =
        localRoverMovementDataSource.sendMovement(movement.toEntity())
}