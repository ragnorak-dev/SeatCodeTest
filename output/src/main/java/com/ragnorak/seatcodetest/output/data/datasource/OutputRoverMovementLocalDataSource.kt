package com.ragnorak.seatcodetest.output.data.datasource

import com.ragnorak.seatcodetest.persistence.RoverMovementEntity
import com.ragnorak.seatcodetest.persistence.dao.RoverMovementsDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OutputRoverMovementLocalDataSource @Inject constructor(
    private val roverMovementDao: RoverMovementsDao
) {

    suspend fun receiveMovement(): Flow<List<RoverMovementEntity>> =
        roverMovementDao.getRoverMovements()
}