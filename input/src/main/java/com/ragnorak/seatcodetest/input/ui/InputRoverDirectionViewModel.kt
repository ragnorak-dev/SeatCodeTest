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
            )
        }
    }
}