package com.ragnorak.seatcodetest.input.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ragnorak.seatcodetest.input.domain.InputRoverMovementRepository
import com.ragnorak.seatcodetest.input.domain.model.Coordinates
import com.ragnorak.seatcodetest.input.domain.model.RoverMovementModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class InputRoverDirectionViewModel @Inject constructor(
    private val repository: InputRoverMovementRepository
) : ViewModel() {

    private val _state: MutableStateFlow<InputRoverMovementState> =
        MutableStateFlow(InputRoverMovementState.Idle)
    val state = _state.asStateFlow()

    fun onIntent(intent: InputRoverMovementIntent) {
        when (intent) {
            InputRoverMovementIntent.SendMovement -> sendMovement()
        }
    }

    val movement = mutableStateOf("")
    val plateauSizeX = mutableStateOf("")
    val plateauSizeY = mutableStateOf("")
    val roverPositionX = mutableStateOf("")
    val roverPositionY = mutableStateOf("")
    val roverDirection = mutableStateOf("")

    private fun sendMovement() {
        viewModelScope.launch {
            _state.value = InputRoverMovementState.Loading

            if (validateMovement()) {
                repository.sendMovement(
                    movement = RoverMovementModel(
                        topRightCorner = Coordinates(
                            x = plateauSizeX.value.toInt(),
                            y = plateauSizeY.value.toInt()
                        ),
                        roverPosition = Coordinates(
                            x = roverPositionX.value.toInt(),
                            y = roverPositionY.value.toInt()
                        ),
                        roverDirection = roverDirection.value,
                        movements = movement.value
                    )
                ).onSuccess {
                    _state.value = InputRoverMovementState.Success
                }.onFailure {
                    _state.value = InputRoverMovementState.Error(it.message ?: "Unknown error")
                }
            }
        }
    }

    private fun validateMovement(): Boolean {
        if (movement.value.isEmpty() ||
            plateauSizeX.value.isEmpty() ||
            plateauSizeY.value.isEmpty() ||
            roverPositionX.value.isEmpty() ||
            roverPositionY.value.isEmpty() ||
            roverDirection.value.isEmpty()
        ) {
            _state.value = InputRoverMovementState.Error("Please fill all fields")
            return false
        }

        if (plateauSizeX.value.toInt() <= 0 || plateauSizeY.value.toInt() <= 0) {
            _state.value = InputRoverMovementState.Error("Plateau size must be greater than 0")
            return false
        }

        if (roverPositionX.value.toInt() < 0 || roverPositionX.value.toInt() > plateauSizeX.value.toInt() ||
            roverPositionY.value.toInt() < 0 || roverPositionY.value.toInt() > plateauSizeY.value.toInt()) {
            _state.value = InputRoverMovementState.Error("Rover position is out of plateau bounds")
            return false
        }

        if (roverDirection.value !in listOf("N", "S", "E", "W")) {
            _state.value = InputRoverMovementState.Error("Invalid rover direction")
            return false
        }
            return true
        }
    }