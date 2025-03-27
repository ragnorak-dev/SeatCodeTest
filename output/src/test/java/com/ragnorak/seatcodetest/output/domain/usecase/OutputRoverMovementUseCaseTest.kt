package com.ragnorak.seatcodetest.output.domain.usecase

import app.cash.turbine.test
import com.ragnorak.seatcodetest.output.CoroutinesTestRule
import com.ragnorak.seatcodetest.output.domain.model.Coordinates
import com.ragnorak.seatcodetest.output.domain.model.OutputRoverMovementModel
import com.ragnorak.seatcodetest.output.domain.repository.OutputRoverMovementRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class OutputRoverMovementUseCaseTest {

    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    private val repository: OutputRoverMovementRepository = mockk()
    private val useCase: OutputRoverMovementUseCase = OutputRoverMovementUseCase(repository)

    @Test
    fun `invoke returns correct final rover position`() = runTest {
        val input = listOf(
            OutputRoverMovementModel(
                topRightCorner = Coordinates(5, 5),
                roverPosition = Coordinates(1, 2),
                roverDirection = "N",
                movements = "LMLMLMLMM"
            )
        )

        coEvery { repository.receiveMovement() } returns flowOf(input)

        useCase().test {
            val result = awaitItem()
            assertEquals(1, result.size)
            assertEquals("1 3 N", result.first().movement)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `invoke ignores movement beyond plateau boundaries`() = runTest {
        val input = listOf(
            OutputRoverMovementModel(
                topRightCorner = Coordinates(5, 5),
                roverPosition = Coordinates(5, 5),
                roverDirection = "N",
                movements = "MMMM"
            )
        )

        coEvery { repository.receiveMovement() } returns flowOf(input)

        useCase().test {
            val result = awaitItem()
            assertEquals("5 5 N", result.first().movement)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `invoke handles movement and direction changes correctly`() = runTest {
        val input = listOf(
            OutputRoverMovementModel(
                topRightCorner = Coordinates(5, 5),
                roverPosition = Coordinates(0, 0),
                roverDirection = "E",
                movements = "MMRMMRMRRM"
            )
        )

        coEvery { repository.receiveMovement() } returns flowOf(input)

        useCase().test {
            val result = awaitItem()
            assertEquals("2 0 E", result.first().movement)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `invoke handles multiple rotations without movement`() = runTest {
        val input = listOf(
            OutputRoverMovementModel(
                topRightCorner = Coordinates(5, 5),
                roverPosition = Coordinates(3, 3),
                roverDirection = "N",
                movements = "LLLL"
            )
        )

        coEvery { repository.receiveMovement() } returns flowOf(input)

        useCase().test {
            val result = awaitItem()
            assertEquals("3 3 N", result.first().movement)
            cancelAndIgnoreRemainingEvents()
        }
    }
}