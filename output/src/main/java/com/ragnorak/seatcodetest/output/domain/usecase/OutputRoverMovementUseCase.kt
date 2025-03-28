package com.ragnorak.seatcodetest.output.domain.usecase

import com.ragnorak.seatcodetest.output.domain.repository.OutputRoverMovementRepository
import com.ragnorak.seatcodetest.output.ui.model.RoverMovementUIModel
import com.ragnorak.seatcodetest.resources.EAST
import com.ragnorak.seatcodetest.resources.LEFT
import com.ragnorak.seatcodetest.resources.MOVE
import com.ragnorak.seatcodetest.resources.NORTH
import com.ragnorak.seatcodetest.resources.RIGHT
import com.ragnorak.seatcodetest.resources.SOUTH
import com.ragnorak.seatcodetest.resources.WEST
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class OutputRoverMovementUseCase @Inject constructor(private val repository: OutputRoverMovementRepository) {

    suspend operator fun invoke(): Flow<List<RoverMovementUIModel>> {
        return repository.receiveMovement().map { movementList ->
            movementList.map {
                val directions = listOf(NORTH, EAST, SOUTH, WEST)

                var x = it.roverPosition.x
                var y = it.roverPosition.y
                var directionIndex = directions.indexOf(it.roverDirection.uppercase())

                it.movements.uppercase().forEach { command ->
                    when (command) {
                        LEFT -> directionIndex = (directionIndex + 3) % 4
                        RIGHT -> directionIndex = (directionIndex + 1) % 4
                        MOVE -> {
                            when (directions[directionIndex]) {
                                NORTH -> if (y < it.topRightCorner.y) y += 1
                                SOUTH -> if (y > 0) y -= 1
                                EAST -> if (x < it.topRightCorner.x) x += 1
                                WEST -> if (x > 0) x -= 1
                            }
                        }
                    }
                }

                RoverMovementUIModel(
                    movement = "$x $y ${directions[directionIndex]}"
                )
            }
        }
    }
}