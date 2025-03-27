package com.ragnorak.seatcodetest.input.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ragnorak.seatcodetest.resources.R


@Composable
fun InputRoverDirectionScreen(viewModel: InputRoverDirectionViewModel = hiltViewModel()) {

    val state = viewModel.state.collectAsStateWithLifecycle()

    Column(
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.vertical_spacing)),
        modifier = Modifier.padding(dimensionResource(id = R.dimen.screen_padding))
    ) {

        PlateauSize(viewModel)

        RoverPosition(viewModel)

        RoverDirection(viewModel)

        RoverMovements(viewModel)
        Button(onClick = { viewModel.onIntent(InputRoverMovementIntent.SendMovement) }) {
            Text(text = "Send")
        }

        when (state.value) {
            InputRoverMovementState.Idle -> {}
            InputRoverMovementState.Loading -> {
                CircularProgressIndicator()
            }
            InputRoverMovementState.Success -> {
                Text(text = "Sent")
            }
            InputRoverMovementState.Error -> {
                Text(text = "Error")
            }
        }
    }
}

@Composable
private fun PlateauSize(viewModel: InputRoverDirectionViewModel) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.horizontal_spacing))
    ) {
        Text(text = "Plateau size")
        TextField(
            modifier = Modifier.weight(1f),
            value = viewModel.plateauSizeX.value,
            placeholder = { Text(text = "X") },
            onValueChange = { viewModel.plateauSizeX.value = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        TextField(
            modifier = Modifier.weight(1f),
            value = viewModel.plateauSizeY.value,
            placeholder = { Text(text = "Y") },
            onValueChange = { viewModel.plateauSizeY.value = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
    }
}

@Composable
private fun RoverPosition(viewModel: InputRoverDirectionViewModel) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.horizontal_spacing))
    ) {
        Text(text = "Rover Position")
        TextField(
            modifier = Modifier.weight(1f),
            value = viewModel.roverPositionX.value,
            placeholder = { Text(text = "X") },
            onValueChange = { viewModel.roverPositionX.value = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        TextField(
            modifier = Modifier.weight(1f),
            value = viewModel.roverPositionY.value,
            placeholder = { Text(text = "Y") },
            onValueChange = { viewModel.roverPositionY.value = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
    }
}

@Composable
private fun RoverDirection(viewModel: InputRoverDirectionViewModel) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.horizontal_spacing))
    ) {
        Text(text = "Rover Direction")
        TextField(
            value = viewModel.roverDirection.value,
            placeholder = { Text(text = "N, S, E, W") },
            onValueChange = { viewModel.roverDirection.value = it },
        )
    }
}

@Composable
private fun RoverMovements(viewModel: InputRoverDirectionViewModel) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.horizontal_spacing))
    ) {
        Text(text = "Rover Movements")
        Button(onClick = { viewModel.movement.value += "L" }) {
            Text(text = "L")
        }
        Button(onClick = { viewModel.movement.value += "R" }) {
            Text(text = "R")
        }
        Button(onClick = { viewModel.movement.value += "M" }) {
            Text(text = "M")
        }
    }
}