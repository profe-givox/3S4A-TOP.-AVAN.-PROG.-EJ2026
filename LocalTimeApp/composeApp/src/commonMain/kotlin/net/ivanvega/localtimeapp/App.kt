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
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.resources.painterResource

import localtimeapp.composeapp.generated.resources.Res
import localtimeapp.composeapp.generated.resources.compose_multiplatform
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@Composable
@Preview
fun App() {
    MaterialTheme {
        var timeAtLocation by remember { mutableStateOf("No location selected") }
        var location by remember { mutableStateOf("Europe/Paris") }

        val miOnClick = {
            timeAtLocation = "13:30"
        }
        Column(
            modifier = Modifier
                .safeContentPadding()
                .fillMaxSize(),
        ) {
            Text(timeAtLocation)

            val mi : (String) -> Unit = { p -> location = p   }
            //val mi : (String) -> Unit = { location = it   }


            //TextField(value = location, onValueChange = mi)

            TextField(value = location, onValueChange = {location = it })

            Button(onClick = miOnClick  ) {
                Text("Show Time At Location")
            }
        }
    }
}


/*
@OptIn(ExperimentalTime::class)
fun currentTimeAt(location: String): String? {
    fun LocalTime.formatted() = "$hour:$minute:$second"

    return try {
        val time = Clock.System.now()
        val zone = TimeZone.of(location)
        val localTime = time.toLocalDateTime(zone).time
        "The time in $location is ${localTime.formatted()}"
    } catch (ex: IllegalTimeZoneException) {
        null
    }
}*/
