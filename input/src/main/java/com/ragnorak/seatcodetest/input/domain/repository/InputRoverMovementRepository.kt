package com.ragnorak.seatcodetest.input.domain.repository

import com.ragnorak.seatcodetest.input.domain.model.RoverMovementModel

interface InputRoverMovementRepository {

    suspend fun sendMovement(movement: RoverMovementModel): Result<Boolean>
}