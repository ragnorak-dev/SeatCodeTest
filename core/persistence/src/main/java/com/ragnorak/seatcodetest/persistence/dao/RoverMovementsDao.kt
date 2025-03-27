package com.ragnorak.seatcodetest.persistence.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.ragnorak.seatcodetest.persistence.RoverMovementEntity

@Dao
interface RoverMovementsDao {

    @Insert
    suspend fun insertRoverMovement(roverMovement: RoverMovementEntity): Long

    @Query("SELECT * FROM RoverMovementEntity")
    suspend fun getRoverMovements(): List<RoverMovementEntity>

    @Delete
    suspend fun deleteRoverMovement(roverMovement: RoverMovementEntity)

    @Query("DELETE FROM rovermovemententity")
    suspend fun deleteAll()
}