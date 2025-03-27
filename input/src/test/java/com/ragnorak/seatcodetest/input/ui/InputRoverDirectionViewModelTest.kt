package com.ragnorak.seatcodetest.input.ui

import com.ragnorak.seatcodetest.input.CoroutinesTestRule
import com.ragnorak.seatcodetest.input.domain.model.Coordinates
import com.ragnorak.seatcodetest.input.domain.model.RoverMovementModel
import com.ragnorak.seatcodetest.input.domain.repository.InputRoverMovementRepository
import com.ragnorak.seatcodetest.input.ui.InputRoverMovementIntent.SendMovement
import com.ragnorak.seatcodetest.input.ui.InputRoverMovementState.Error
import com.ragnorak.seatcodetest.input.ui.InputRoverMovementState.Success
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class InputRoverDirectionViewModelTest {


    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()


    private val repository: InputRoverMovementRepository = mockk()
    private val viewModel: InputRoverDirectionViewModel = InputRoverDirectionViewModel(repository)

    @Test
    fun `onIntent SendMovement emits success when data is valid`() = runTest {
        viewModel.plateauSizeX.value = "5"
        viewModel.plateauSizeY.value = "5"
        viewModel.roverPositionX.value = "1"
        viewModel.roverPositionY.value = "2"
        viewModel.roverDirection.value = "N"
        viewModel.movement.value = "LMLMLMLMM"

        val model = RoverMovementModel(
            topRightCorner = Coordinates(5, 5),
            roverPosition = Coordinates(1, 2),
            roverDirection = "N",
            movements = "LMLMLMLMM"
        )

        coEvery { repository.sendMovement(model) } returns Result.success(true)

        viewModel.onIntent(SendMovement)
        advanceUntilIdle()
        assertTrue(viewModel.state.value is Success)
    }

    @Test
    fun `onIntent SendMovement emits error when fields are empty`() = runTest {
        viewModel.onIntent(SendMovement)
        advanceUntilIdle()
        assertTrue(viewModel.state.value is Error)
    }

    @Test
    fun `onIntent SendMovement emits error when direction is invalid`() = runTest {
        viewModel.plateauSizeX.value = "5"
        viewModel.plateauSizeY.value = "5"
        viewModel.roverPositionX.value = "1"
        viewModel.roverPositionY.value = "2"
        viewModel.roverDirection.value = "H"
        viewModel.movement.value = "LMLMLMLMM"

        viewModel.onIntent(SendMovement)
        advanceUntilIdle()
        assertTrue(viewModel.state.value is Error)
    }

}