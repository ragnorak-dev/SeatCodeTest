package com.ragnorak.seatcodetest.input.data.repository

import com.ragnorak.seatcodetest.input.CoroutinesTestRule
import com.ragnorak.seatcodetest.input.data.datasource.LocalRoverMovementDataSource
import com.ragnorak.seatcodetest.input.data.mapper.toEntity
import com.ragnorak.seatcodetest.input.domain.model.Coordinates
import com.ragnorak.seatcodetest.input.domain.model.RoverMovementModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class InputRoverMovementRepositoryImplTest {


    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    private val dataSource: LocalRoverMovementDataSource = mockk()
    private val repository = InputRoverMovementRepositoryImpl(dataSource)

    private val model = RoverMovementModel(
        topRightCorner = Coordinates(x = 5, y = 5),
        roverPosition = Coordinates( x = 1, y = 2),
        roverDirection = "N",
        movements = "LMLMLMLMM"
    )

    @Test
    fun `sendMovement returns success when data source returns success`() = runTest {
        val entity = model.toEntity()
        coEvery { dataSource.sendMovement(entity) } returns Result.success(true)

        val result = repository.sendMovement(model)

        assertTrue(result.isSuccess)
        assertEquals(true, result.getOrNull())
    }

    @Test
    fun `sendMovement returns failure when data source returns failure`() = runTest {
        val entity = model.toEntity()
        val exception = Exception("Insert failed")
        coEvery { dataSource.sendMovement(entity) } returns Result.failure(exception)

        val result = repository.sendMovement(model)

        assertTrue(result.isFailure)
        assertEquals(exception, result.exceptionOrNull())
    }
}