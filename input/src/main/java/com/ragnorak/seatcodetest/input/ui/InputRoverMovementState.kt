package com.ragnorak.seatcodetest.input.ui

sealed class InputRoverMovementState {
    data object Idle: InputRoverMovementState()
    data object Success: InputRoverMovementState()
    data object Error: InputRoverMovementState()
    data object Loading: InputRoverMovementState()
}
