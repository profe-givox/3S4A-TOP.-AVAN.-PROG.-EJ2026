package net.ivanvega.localtimeapp

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import localtimeapp.composeapp.generated.resources.eg
import localtimeapp.composeapp.generated.resources.fr
import localtimeapp.composeapp.generated.resources.id
import localtimeapp.composeapp.generated.resources.jp
import localtimeapp.composeapp.generated.resources.mx
import org.jetbrains.compose.resources.DrawableResource
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

data class Country(val name: String, val zone: TimeZone, val image: DrawableResource)

fun countries() = listOf(
    Country("Japan", TimeZone.of("Asia/Tokyo", ), Res.drawable.jp),
    Country("France", TimeZone.of("Europe/Paris") , Res.drawable.fr),
    Country("Mexico", TimeZone.of("America/Mexico_City"), Res.drawable.mx),
    Country("Indonesia", TimeZone.of("Asia/Jakarta"), Res.drawable.id),
    Country("Egypt", TimeZone.of("Africa/Cairo"), Res.drawable.eg),
)

/*
@Composable
@Preview
fun App(countries: List<Country> = countries()) {
    MaterialTheme {
        var timeAtLocation by remember { mutableStateOf("No location selected") }
        var location by remember { mutableStateOf("Europe/Paris") }
        var showCountries by remember { mutableStateOf(true) }

        val miOnClick = {
            //timeAtLocation = currentTimeAt(location,    "") ?: "Invalid location"
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

            //TextField(value = location, onValueChange = {location = it },modifier = Modifier.padding(top = 10.dp))
            Row(modifier = Modifier.padding(start = 20.dp, top = 10.dp).fillMaxWidth().wrapContentHeight()) {
                DropdownMenu(
                    expanded = showCountries,
                    onDismissRequest = { showCountries = false }
                ) {
                    countries().forEach { (name, zone) ->
                        DropdownMenuItem(
                            text = { Text(name) },
                            onClick = {
                                timeAtLocation = currentTimeAt(name, zone)
                                showCountries = false
                            }
                        )
                    }
                }
            }

            Button(onClick = miOnClick,modifier = Modifier.padding(top = 10.dp)  ) {
                Text("Show Time At Location")
            }
        }
    }
}
*/
@Composable
@Preview
fun App(countries: List<Country> = countries()) {
    MaterialTheme {
        var showCountries by remember { mutableStateOf(false) }
        var timeAtLocation by remember { mutableStateOf("No location selected") }

        Column(
            modifier = Modifier
                .padding(20.dp)
                .safeContentPadding()
                .fillMaxSize(),
        ) {
            Text(
                timeAtLocation,
                style = TextStyle(fontSize = 20.sp),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth().align(Alignment.CenterHorizontally)
            )
            Row(modifier = Modifier.padding(start = 20.dp, top = 10.dp)) {
                DropdownMenu(
                    expanded = showCountries,
                    onDismissRequest = { showCountries = false }
                ) {
                    countries().forEach { (name, zone, image) ->
                        DropdownMenuItem(
                            text = {

                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Image(
                                        painterResource(image),
                                        modifier = Modifier.size(50.dp).padding(end = 10.dp),
                                        contentDescription = "$name flag"
                                    )
                                    Text(name)
                                }

                            },
                            onClick = {
                                timeAtLocation = currentTimeAt(name, zone)
                                showCountries = false
                            }
                        )
                    }
                }
            }

            Button(modifier = Modifier.padding(start = 20.dp, top = 10.dp),
                onClick = { showCountries = !showCountries }) {
                Text("Select Location")
            }
        }
    }
}

@OptIn(ExperimentalTime::class)
fun currentTimeAt(location: String, zone: TimeZone): String {
    fun LocalTime.formatted() = "$hour:$minute:$second"

    val time = Clock.System.now()
    val localTime = time.toLocalDateTime(zone).time

    return "The time in $location is ${localTime.formatted()}"
}
