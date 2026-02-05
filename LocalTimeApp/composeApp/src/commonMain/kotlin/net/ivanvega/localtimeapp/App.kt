package net.ivanvega.localtimeapp

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextAlign.Companion.Center
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.datetime.IllegalTimeZoneException
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
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
            timeAtLocation = currentTimeAt(location) ?: "Invalid location"
        }
        Column(
            modifier = Modifier
                .safeContentPadding()
                .fillMaxSize()
                .padding(20.dp),
        ) {
            Text(
                timeAtLocation,
                style = TextStyle(fontSize = 20.sp,color=Color.Cyan),
                textAlign = Center,
                modifier = Modifier.fillMaxWidth().align(Alignment.CenterHorizontally)
            )

            val mi : (String) -> Unit = { p -> location = p   }
            //val mi : (String) -> Unit = { location = it   }


            //TextField(value = location, onValueChange = mi)

            TextField(value = location, onValueChange = {location = it },modifier = Modifier.padding(top = 10.dp))

            Button(onClick = miOnClick,modifier = Modifier.padding(top = 10.dp)  ) {
                Text("Show Time At Location")
            }
        }
    }
}


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
}
