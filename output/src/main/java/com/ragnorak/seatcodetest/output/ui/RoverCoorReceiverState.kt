package com.ragnorak.seatcodetest.output.ui

import com.ragnorak.seatcodetest.output.ui.model.RoverMovementUIModel

sealed class RoverCoorReceiverState {
    data object Idle : RoverCoorReceiverState()
    data object Loading : RoverCoorReceiverState()
    data class Success(val data: List<RoverMovementUIModel>) : RoverCoorReceiverState()
    data class Error(val message: String) : RoverCoorReceiverState()
}
