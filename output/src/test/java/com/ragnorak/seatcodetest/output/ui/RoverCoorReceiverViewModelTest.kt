package com.ragnorak.seatcodetest.output.ui

import com.ragnorak.seatcodetest.output.CoroutinesTestRule
import com.ragnorak.seatcodetest.output.domain.usecase.OutputRoverMovementUseCase
import com.ragnorak.seatcodetest.output.ui.model.RoverMovementUIModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class RoverCoorReceiverViewModelTest {

    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    private val useCase: OutputRoverMovementUseCase = mockk(relaxed = true)
    private val viewModel: RoverCoorReceiverViewModel = RoverCoorReceiverViewModel(useCase)

    @Test
    fun `onIntent ReceiveMovement updates state to Success`() = runTest {
        val movementList = listOf(
            RoverMovementUIModel("1 3 N")
        )

        coEvery { useCase() } returns flowOf(movementList)

        viewModel.onIntent(RoverCoorReceiverIntent.ReceiveMovement)
        advanceUntilIdle()

        val state = viewModel.state.value
        assertTrue(state is RoverCoorReceiverState.Success)
        assertEquals(movementList, (state as RoverCoorReceiverState.Success).data)
    }

    @Test
    fun `onIntent ReceiveMovement emits empty Success list when data is empty`() = runTest {
        coEvery { useCase() } returns flowOf(emptyList())

        viewModel.onIntent(RoverCoorReceiverIntent.ReceiveMovement)
        advanceUntilIdle()

        val state = viewModel.state.value
        assertTrue(state is RoverCoorReceiverState.Success)
        assertTrue((state as RoverCoorReceiverState.Success).data.isEmpty())
    }

    @Test
    fun `onIntent ReceiveMovement emits Error when exception is thrown`() = runTest {
        coEvery { useCase() } returns flow { throw Exception("error") }

        viewModel.onIntent(RoverCoorReceiverIntent.ReceiveMovement)
        advanceUntilIdle()

        val state = viewModel.state.value
        assertTrue(state is RoverCoorReceiverState.Error)
    }

    @Test
    fun `onIntent ReceiveMovement emits initial state before data`() = runTest {
        coEvery { useCase() } returns flowOf(emptyList())

        assertTrue(viewModel.state.value is RoverCoorReceiverState.Idle)

        viewModel.onIntent(RoverCoorReceiverIntent.ReceiveMovement)
        advanceUntilIdle()

        val state = viewModel.state.value
        assertTrue(state is RoverCoorReceiverState.Success)
    }
}

