package com.ragnorak.seatcodetest.output.domain.repository

import com.ragnorak.seatcodetest.output.domain.model.OutputRoverMovementModel
import kotlinx.coroutines.flow.Flow

interface OutputRoverMovementRepository {
    suspend fun receiveMovement(): Flow<List<OutputRoverMovementModel>>

}