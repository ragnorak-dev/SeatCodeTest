package com.ragnorak.seatcodetest.input.domain

import com.ragnorak.seatcodetest.input.domain.model.RoverMovementModel

interface InputRoverMovementRepository {

    suspend fun sendMovement(movement: RoverMovementModel)
}