package com.ragnorak.seatcodetest.output.ui

sealed class RoverCoorReceiverIntent {
    data object ReceiveMovement : RoverCoorReceiverIntent()
}
