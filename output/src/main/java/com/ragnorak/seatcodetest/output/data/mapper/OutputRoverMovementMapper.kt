package com.ragnorak.seatcodetest.output.data.mapper

import com.ragnorak.seatcodetest.output.domain.model.Coordinates
import com.ragnorak.seatcodetest.output.domain.model.OutputRoverMovementModel
import com.ragnorak.seatcodetest.persistence.CoordinatesEntity
import com.ragnorak.seatcodetest.persistence.RoverMovementEntity

fun RoverMovementEntity.toModel() =
    OutputRoverMovementModel(
        topRightCorner = topRightCorner.toModel(),
        roverPosition = roverPosition.toModel(),
        roverDirection = roverDirection,
        movements = movements
    )

fun CoordinatesEntity.toModel() =
    Coordinates(x = x, y = y)