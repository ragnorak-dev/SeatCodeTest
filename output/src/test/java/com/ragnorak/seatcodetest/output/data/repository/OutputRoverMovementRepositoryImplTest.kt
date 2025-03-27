
package com.ragnorak.seatcodetest.output.data.repository

import app.cash.turbine.test
import com.ragnorak.seatcodetest.output.CoroutinesTestRule
import com.ragnorak.seatcodetest.output.data.datasource.OutputRoverMovementLocalDataSource
import com.ragnorak.seatcodetest.output.data.mapper.toModel
import com.ragnorak.seatcodetest.persistence.CoordinatesEntity
import com.ragnorak.seatcodetest.persistence.RoverMovementEntity
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class OutputRoverMovementRepositoryImplTest {

 @get:Rule
 var coroutinesTestRule = CoroutinesTestRule()

 private val dataSource: OutputRoverMovementLocalDataSource = mockk()
 private val repository: OutputRoverMovementRepositoryImpl = OutputRoverMovementRepositoryImpl(dataSource)

 private val roverMovementEntity = RoverMovementEntity(
  id = 1,
  topRightCorner = CoordinatesEntity(id = 0, x = 5, y = 5),
  roverPosition = CoordinatesEntity(id = 0, x = 1, y = 2),
  roverDirection = "N",
  movements = "LMLMLMLMM"
 )


 @Test
 fun `receiveMovement emits mapped models from data source`() = runTest {
  val entityList = listOf(roverMovementEntity)
  val expectedModelList = entityList.map { it.toModel() }

  every { dataSource.receiveMovement() } returns flowOf(entityList)

  repository.receiveMovement().test {
   val item = awaitItem()
   assertEquals(expectedModelList, item)
   cancelAndIgnoreRemainingEvents()
  }
 }
}