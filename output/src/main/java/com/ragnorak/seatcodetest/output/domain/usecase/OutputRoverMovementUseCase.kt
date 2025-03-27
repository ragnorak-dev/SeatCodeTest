package com.ragnorak.seatcodetest.output.domain.usecase

import com.ragnorak.seatcodetest.output.domain.repository.OutputRoverMovementRepository
import com.ragnorak.seatcodetest.output.ui.model.RoverMovementUIModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class OutputRoverMovementUseCase @Inject constructor(private val repository: OutputRoverMovementRepository) {

    suspend operator fun invoke(): Flow<List<RoverMovementUIModel>> {
        return repository.receiveMovement().map { movementList ->
            movementList.map {
                val directions = listOf("N", "E", "S", "W")

                var x = it.roverPosition.x
                var y = it.roverPosition.y
                var directionIndex = directions.indexOf(it.roverDirection.uppercase())

                it.movements.uppercase().forEach { command ->
                    when (command) {
                        'L' -> directionIndex = (directionIndex + 3) % 4
                        'R' -> directionIndex = (directionIndex + 1) % 4
                        'M' -> {
                            when (directions[directionIndex]) {
                                "N" -> if (y < it.topRightCorner.y) y += 1
                                "S" -> if (y > 0) y -= 1
                                "E" -> if (x < it.topRightCorner.x) x += 1
                                "W" -> if (x > 0) x -= 1
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