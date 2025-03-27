package com.ragnorak.seatcodetest.input.ui

sealed class InputRoverMovementIntent {
    data object SendMovement: InputRoverMovementIntent()
}