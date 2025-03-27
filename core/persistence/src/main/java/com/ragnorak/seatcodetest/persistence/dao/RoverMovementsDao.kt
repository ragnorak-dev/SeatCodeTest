package com.ragnorak.seatcodetest.persistence.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.ragnorak.seatcodetest.persistence.RoverMovementEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RoverMovementsDao {

    @Insert
    suspend fun insertRoverMovement(roverMovement: RoverMovementEntity): Long

    @Query("SELECT * FROM RoverMovementEntity")
    fun getRoverMovements(): Flow<List<RoverMovementEntity>>

    @Delete
    suspend fun deleteRoverMovement(roverMovement: RoverMovementEntity)

    @Query("DELETE FROM rovermovemententity")
    suspend fun deleteAll()
}