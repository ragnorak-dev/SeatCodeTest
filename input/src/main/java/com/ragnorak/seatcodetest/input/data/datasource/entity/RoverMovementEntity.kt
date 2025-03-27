package com.ragnorak.seatcodetest.input.data.datasource.entity

data class RoverMovementEntity(
    val topRightCorner: CoordinatesEntity,
    val roverPosition: CoordinatesEntity,
    val roverDirection: String,
    val movements: String
)

data class CoordinatesEntity(
    val x: Int,
    val y: Int
)
