package net.ivanvega.demokcpdatabasedesktop

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import net.ivanvega.demokcpdatabasedesktop.cache.JvmDatabaseDriverFactory
import net.ivanvega.demokcpdatabasedesktop.data.JvmDriverFactory

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "demokcpdatabasedesktop",
    ) {
        App(JvmDatabaseDriverFactory())
    }
}