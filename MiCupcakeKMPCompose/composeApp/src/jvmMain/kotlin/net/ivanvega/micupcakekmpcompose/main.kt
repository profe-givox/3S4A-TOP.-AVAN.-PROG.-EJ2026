package net.ivanvega.micupcakekmpcompose

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import net.ivanvega.micupcakekmpcompose.screens.CupcakeApp
import net.ivanvega.micupcakekmpcompose.screens.CupcakeScreen

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "micupcakekmpcompose",
    ) {
        App()
    }
}