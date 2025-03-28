package com.ragnorak.seatcodetest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.ragnorak.seatcodetest.input.ui.InputRoverDirectionScreen
import com.ragnorak.seatcodetest.output.ui.RoverCoorReceiverScreen
import com.ragnorak.seatcodetest.ui.theme.SeatCodeTestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SeatCodeTestTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        InputRoverDirectionScreen()
                        RoverCoorReceiverScreen()
                    }
                }
            }
        }
    }
}