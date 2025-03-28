package com.ragnorak.seatcodetest.output.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ragnorak.seatcodetest.resources.CURRENT_POSITION_ID
import com.ragnorak.seatcodetest.resources.POSITION_HISTORY_ID
import com.ragnorak.seatcodetest.resources.R

@Composable
fun RoverCoorReceiverScreen(viewModel: RoverCoorReceiverViewModel = hiltViewModel()) {

    val state = viewModel.state.collectAsStateWithLifecycle()

    when (val value = state.value) {
        RoverCoorReceiverState.Idle -> {
            viewModel.onIntent(RoverCoorReceiverIntent.ReceiveMovement)
        }

        is RoverCoorReceiverState.Success -> {

            Row(
                modifier = Modifier
                    .padding(dimensionResource(R.dimen.screen_padding))
                    .fillMaxSize()
            ) {


                Text(
                    modifier = Modifier
                        .weight(2F)
                        .testTag(CURRENT_POSITION_ID),
                    text = "${stringResource(R.string.current_position)}: ${
                        value.data.lastOrNull()?.movement ?: ""}",
                    style = MaterialTheme.typography.titleLarge
                )

                LazyColumn(
                    modifier = Modifier
                        .weight(1F)
                        .testTag(POSITION_HISTORY_ID),
                    horizontalAlignment = Alignment.Start
                ) {
                    item {
                        Text(text = stringResource(R.string.movement_history))
                    }
                    items(value.data.size, key = { it }) {
                        Text(text = value.data[it].movement)
                    }
                }

            }

        }

        is RoverCoorReceiverState.Error -> {}
        RoverCoorReceiverState.Loading -> {}
    }

}
