package com.ragnorak.seatcodetest.input.ui

sealed class InputRoverMovementState {
    data object Idle: InputRoverMovementState()
    data object Success: InputRoverMovementState()
    data class Error(val message: String): InputRoverMovementState()
    data object Loading: InputRoverMovementState()
}
