package com.ragnorak.seatcodetest.input.domain.model

data class RoverMovementModel(
    val topRightCorner: Coordinates,
    val roverPosition: Coordinates,
    val roverDirection: String,
    val movements: String
)

data class Coordinates(
    val x: Int,
    val y: Int
)