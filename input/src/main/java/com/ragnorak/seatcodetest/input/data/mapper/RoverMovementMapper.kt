package com.ragnorak.seatcodetest.input.data.mapper

import com.ragnorak.seatcodetest.persistence.CoordinatesEntity
import com.ragnorak.seatcodetest.persistence.RoverMovementEntity
import com.ragnorak.seatcodetest.input.domain.model.Coordinates
import com.ragnorak.seatcodetest.input.domain.model.RoverMovementModel

fun RoverMovementModel.toEntity() =
    RoverMovementEntity(
        topRightCorner = topRightCorner.toEntity(),
        roverPosition = roverPosition.toEntity(),
        roverDirection = roverDirection,
        movements = movements
    )

fun Coordinates.toEntity() =
    CoordinatesEntity(x = x, y = y)