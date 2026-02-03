package net.ivanvega.localtimeapp

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.resources.painterResource

import localtimeapp.composeapp.generated.resources.Res
import localtimeapp.composeapp.generated.resources.compose_multiplatform

@Composable
@Preview
fun App() {
    MaterialTheme {
        var timeAtLocation by remember { mutableStateOf("No location selected") }

        Column(
            modifier = Modifier
                .safeContentPadding()
                .fillMaxSize(),
        ) {
            Text(timeAtLocation)
            Button(onClick = { timeAtLocation = "13:30" }) {
                Text("Show Time At Location")
            }
        }
    }
}