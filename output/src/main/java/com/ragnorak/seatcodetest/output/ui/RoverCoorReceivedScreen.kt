package com.ragnorak.seatcodetest.output.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ragnorak.seatcodetest.resources.R

@Composable
fun RoverCoorReceiverScreen(viewModel: RoverCoorReceiverViewModel = hiltViewModel()) {

    val state = viewModel.state.collectAsStateWithLifecycle()

    when (val value = state.value) {
        RoverCoorReceiverState.Idle -> {
            viewModel.onIntent(RoverCoorReceiverIntent.ReceiveMovement)
        }
        is RoverCoorReceiverState.Success -> {

             Box(modifier = Modifier.fillMaxSize()) {
                 LazyColumn(
                     modifier = Modifier.align(Alignment.TopEnd)
                 ) {
                     items(value.data.size, key = { it }) {
                         Text(text = value.data[it].movement)
                     }
                 }
                 Text(
                     modifier = Modifier.align(Alignment.Center),
                     text = "${stringResource(R.string.current_position)}: ${value.data.last().movement}",
                     style = MaterialTheme.typography.titleLarge
                 )
             }

        }
        is RoverCoorReceiverState.Error -> {}
        RoverCoorReceiverState.Loading -> {}
    }

}
