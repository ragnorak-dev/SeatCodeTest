package com.ragnorak.seatcodetest.output.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ragnorak.seatcodetest.output.domain.usecase.OutputRoverMovementUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoverCoorReceiverViewModel @Inject constructor(private val getRoverMovementUseCase: OutputRoverMovementUseCase) : ViewModel() {

    private val _state: MutableStateFlow<RoverCoorReceiverState> = MutableStateFlow(RoverCoorReceiverState.Idle)
    val state = _state.asStateFlow()

    fun onIntent(intent: RoverCoorReceiverIntent) {
        when (intent) {
            RoverCoorReceiverIntent.ReceiveMovement -> receiveMovement()
        }
    }

    private fun receiveMovement() {
        viewModelScope.launch {
            getRoverMovementUseCase().collect { movementList ->

                _state.value = RoverCoorReceiverState.Success(movementList)
            }
        }
    }

}
