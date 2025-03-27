package com.ragnorak.seatcodetest.input.data.datasource

import com.ragnorak.seatcodetest.input.exceptions.SendMovementException
import com.ragnorak.seatcodetest.persistence.RoverMovementEntity
import com.ragnorak.seatcodetest.persistence.dao.RoverMovementsDao
import javax.inject.Inject

class LocalRoverMovementDataSource @Inject constructor(private val roverMovementsDao: RoverMovementsDao) {

    suspend fun sendMovement(roverMovementEntity: RoverMovementEntity): Result<Boolean> {
        val result = roverMovementsDao.insertRoverMovement(roverMovementEntity)
        if (result > 0) {
            return Result.success(true)
        }
        return Result.failure(SendMovementException())
    }
}
