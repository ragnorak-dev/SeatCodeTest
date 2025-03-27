package com.ragnorak.seatcodetest.output.domain.model

data class OutputRoverMovementModel(
    val topRightCorner: Coordinates,
    val roverPosition: Coordinates,
    val roverDirection: String,
    val movements: String
)

data class Coordinates(
    val x: Int,
    val y: Int
)