// Test file content (OutputRoverMovementLocalDataSourceTest.kt)

package com.ragnorak.seatcodetest.output.data.datasource

import app.cash.turbine.test
import com.ragnorak.seatcodetest.output.CoroutinesTestRule
import com.ragnorak.seatcodetest.persistence.CoordinatesEntity
import com.ragnorak.seatcodetest.persistence.RoverMovementEntity
import com.ragnorak.seatcodetest.persistence.dao.RoverMovementsDao
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class OutputRoverMovementLocalDataSourceTest {

    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    private val dao: RoverMovementsDao = mockk()
    private val dataSource: OutputRoverMovementLocalDataSource =
        OutputRoverMovementLocalDataSource(dao)

    private val roverMovementEntity = RoverMovementEntity(
        id = 1,
        topRightCorner = CoordinatesEntity(id = 0, x = 5, y = 5),
        roverPosition = CoordinatesEntity(id = 0, x = 1, y = 2),
        roverDirection = "N",
        movements = "LMLMLMLMM"
    )

    @Test
    fun `receiveMovement returns flow from dao`() = runTest {
        val mockData = listOf(roverMovementEntity)

        every { dao.getRoverMovements() } returns flowOf(mockData)

        dataSource.receiveMovement().test {
            val result = awaitItem()
            assertEquals(mockData, result)
            cancelAndIgnoreRemainingEvents()
        }
    }
}