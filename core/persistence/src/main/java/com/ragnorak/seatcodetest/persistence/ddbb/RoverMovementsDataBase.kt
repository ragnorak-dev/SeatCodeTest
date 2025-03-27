package com.ragnorak.seatcodetest.persistence.ddbb

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ragnorak.seatcodetest.persistence.CoordinatesConverter
import com.ragnorak.seatcodetest.persistence.RoverMovementEntity
import com.ragnorak.seatcodetest.persistence.dao.RoverMovementsDao


@Database(
    entities = [RoverMovementEntity::class],
    version = 1
)
@TypeConverters(CoordinatesConverter::class)
abstract class RoverMovementsDataBase : RoomDatabase() {
    abstract fun roverMovementsDao(): RoverMovementsDao
}