package net.mr.miprimerkmp

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "miprimerkmp",
    ) {
        App()
    }
}