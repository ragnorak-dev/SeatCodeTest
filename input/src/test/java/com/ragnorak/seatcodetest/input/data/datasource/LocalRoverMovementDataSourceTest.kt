package com.ragnorak.seatcodetest.input.data.datasource

import com.ragnorak.seatcodetest.input.CoroutinesTestRule
import com.ragnorak.seatcodetest.input.exceptions.SendMovementException
import com.ragnorak.seatcodetest.persistence.CoordinatesEntity
import com.ragnorak.seatcodetest.persistence.RoverMovementEntity
import com.ragnorak.seatcodetest.persistence.dao.RoverMovementsDao
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class LocalRoverMovementDataSourceTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    private val fakeDao: RoverMovementsDao = mockk()
    private val dataSource: LocalRoverMovementDataSource = LocalRoverMovementDataSource(fakeDao)

    private val entity = RoverMovementEntity(
        topRightCorner = CoordinatesEntity(id = 0, x = 5, y = 5),
        roverPosition = CoordinatesEntity(id = 0, x = 1, y = 2),
        roverDirection = "N",
        movements = "LMLMLMLMM"
    )

    @Test
    fun `sendMovement returns success when insert is successful`() = runTest {
        coEvery { fakeDao.insertRoverMovement(entity) } returns 1L

        val result = dataSource.sendMovement(entity)

        assertTrue(result.isSuccess)
        assertEquals(true, result.getOrNull())
    }

    @Test
    fun `sendMovement returns failure when insert fails`() = runTest {
        coEvery { fakeDao.insertRoverMovement(entity) } returns 0L

        val result = dataSource.sendMovement(entity)

        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is SendMovementException)
    }
}
