package com.ragnorak.seatcodetest.persistence

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter

@Entity
data class RoverMovementEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val topRightCorner: CoordinatesEntity,
    val roverPosition: CoordinatesEntity,
    val roverDirection: String,
    val movements: String
)

@Entity
data class CoordinatesEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val x: Int,
    val y: Int
)

class CoordinatesConverter {
    @TypeConverter
    fun fromCoordinates(coord: CoordinatesEntity): String {
        return "${coord.x},${coord.y}"
    }

    @TypeConverter
    fun toCoordinates(value: String): CoordinatesEntity {
        val (x, y) = value.split(",").map { it.toInt() }
        return CoordinatesEntity(0, x, y) // El ID quizás no lo necesites
    }
}