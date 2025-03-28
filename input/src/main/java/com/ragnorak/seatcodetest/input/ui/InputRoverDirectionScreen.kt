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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ragnorak.seatcodetest.resources.ERROR_TEXT_ID
import com.ragnorak.seatcodetest.resources.LEFT
import com.ragnorak.seatcodetest.resources.MOVE
import com.ragnorak.seatcodetest.resources.PLATEAU_SIZE_X_ID
import com.ragnorak.seatcodetest.resources.PLATEAU_SIZE_Y_ID
import com.ragnorak.seatcodetest.resources.R
import com.ragnorak.seatcodetest.resources.RIGHT
import com.ragnorak.seatcodetest.resources.ROVER_DIRECTION_ID
import com.ragnorak.seatcodetest.resources.ROVER_MOVEMENTS_LEFT
import com.ragnorak.seatcodetest.resources.ROVER_MOVEMENTS_MOVE
import com.ragnorak.seatcodetest.resources.ROVER_MOVEMENTS_RIGHT
import com.ragnorak.seatcodetest.resources.ROVER_POSITION_X_ID
import com.ragnorak.seatcodetest.resources.ROVER_POSITION_Y_ID
import com.ragnorak.seatcodetest.resources.SEND_BUTTON_ID
import com.ragnorak.seatcodetest.resources.SUCCESS_TEXT_ID


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


        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.horizontal_spacing))
        ) {
            Button(modifier = Modifier.testTag(SEND_BUTTON_ID),
                onClick = { viewModel.onIntent(InputRoverMovementIntent.SendMovement) }) {
                Text(text = "Send")
            }

            when (state.value) {
                InputRoverMovementState.Idle -> {}
                InputRoverMovementState.Loading -> {
                    CircularProgressIndicator()
                }

                InputRoverMovementState.Success -> {
                    Text(modifier = Modifier.testTag(SUCCESS_TEXT_ID),
                        text = stringResource(R.string.sent),
                        color = Color.Green)
                }

                is InputRoverMovementState.Error -> {
                    Text(modifier = Modifier.testTag(ERROR_TEXT_ID),
                        text = (state.value as InputRoverMovementState.Error).message,
                        color = Color.Red
                    )
                }
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
        Text(text = stringResource(R.string.plateau_size))
        TextField(
            modifier = Modifier
                .weight(1f)
                .testTag(PLATEAU_SIZE_X_ID),
            value = viewModel.plateauSizeX.value,
            placeholder = { Text(text = stringResource(R.string.position_x)) },
            onValueChange = { viewModel.plateauSizeX.value = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        TextField(
            modifier = Modifier
                .weight(1f)
                .testTag(PLATEAU_SIZE_Y_ID),
            value = viewModel.plateauSizeY.value,
            placeholder = { Text(text = stringResource(R.string.position_y)) },
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
        Text(text = stringResource(R.string.rover_position))
        TextField(
            modifier = Modifier
                .weight(1f)
                .testTag(ROVER_POSITION_X_ID),
            value = viewModel.roverPositionX.value,
            placeholder = { Text(text = stringResource(R.string.position_x)) },
            onValueChange = { viewModel.roverPositionX.value = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        TextField(
            modifier = Modifier
                .weight(1f)
                .testTag(ROVER_POSITION_Y_ID),
            value = viewModel.roverPositionY.value,
            placeholder = { Text(text = stringResource(R.string.position_y)) },
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
        Text(text = stringResource(R.string.rover_direction))
        TextField(modifier = Modifier.testTag(ROVER_DIRECTION_ID),
            value = viewModel.roverDirection.value,
            placeholder = { Text(text = stringResource(R.string.direction_placeholder)) },
            onValueChange = { viewModel.roverDirection.value = it },
        )
    }
}

@Composable
private fun RoverMovements(viewModel: InputRoverDirectionViewModel) {
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.horizontal_spacing))
        ) {
            Text(text = stringResource(R.string.rover_movements))
            Button(modifier = Modifier.testTag(ROVER_MOVEMENTS_LEFT),
                onClick = { viewModel.movement.value += LEFT }) {
                Text(text = stringResource(R.string.left))
            }
            Button(modifier = Modifier.testTag(ROVER_MOVEMENTS_RIGHT),
                onClick = { viewModel.movement.value += RIGHT }) {
                Text(text = stringResource(R.string.right))
            }
            Button(modifier = Modifier.testTag(ROVER_MOVEMENTS_MOVE),
                onClick = { viewModel.movement.value += MOVE }) {
                Text(text = stringResource(R.string.move))
            }
        }
        Text(text = viewModel.movement.value)
    }
}