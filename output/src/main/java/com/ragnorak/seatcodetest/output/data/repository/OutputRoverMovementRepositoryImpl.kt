package com.ragnorak.seatcodetest.output.data.repository

import com.ragnorak.seatcodetest.output.data.datasource.OutputRoverMovementLocalDataSource
import com.ragnorak.seatcodetest.output.data.mapper.toModel
import com.ragnorak.seatcodetest.output.domain.model.OutputRoverMovementModel
import com.ragnorak.seatcodetest.output.domain.repository.OutputRoverMovementRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class OutputRoverMovementRepositoryImpl @Inject constructor(
    private val outputRoverMovementLocalDataSource: OutputRoverMovementLocalDataSource
) : OutputRoverMovementRepository {

    override suspend fun receiveMovement(): Flow<List<OutputRoverMovementModel>> =
        outputRoverMovementLocalDataSource.receiveMovement().map { movementEntityList ->
            movementEntityList.map { it.toModel() }
        }

}