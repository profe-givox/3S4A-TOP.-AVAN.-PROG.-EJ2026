package net.ivanvega.kotlinmultiplatformdemodesktop

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.window.DialogWindow
import androidx.compose.ui.window.MenuBar
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import kotlinx.coroutines.delay

fun main() = application {
    /*var fileName by remember { mutableStateOf("Untitled") }
    Window(
        //onCloseRequest = {  println("Presionando boton cerrar de la app")  },
        //onCloseRequest = ::exitApplication,
        onCloseRequest = {  exitApplication() },
        title = "$fileName - Editor",
    ) {
        //App()
        Button(onClick = { fileName = "note.txt" }) {
            Text("Save")
        }
    }*/
    //DosVentanatasCondicioopn()
    //DosVentanasYCoroutine(::exitApplication)

    val applicationState = remember { MyApplicationState() }

    for (window in applicationState.windows) {
        key(window) {
            MyWindow(window)
        }
    }
}




private class MyApplicationState {
    val windows = mutableStateListOf<MyWindowState>()

    init {
        windows += MyWindowState("Initial window")
    }

    fun openNewWindow() {
        windows += MyWindowState("Window ${windows.size}")
    }

    fun exit() {
        windows.clear()
    }

    private fun MyWindowState(
        title: String
    ) = MyWindowState(
        title,
        openNewWindow = ::openNewWindow,
        exit = ::exit,
        windows::remove
    )
}

private class MyWindowState(
    val title: String,
    val openNewWindow: () -> Unit,
    val exit: () -> Unit,
    private val close: (MyWindowState) -> Unit
) {
    fun close() = close(this)
}


@Composable
private fun MyWindow(
    state: MyWindowState
) = Window(onCloseRequest = state::close, title = state.title) {
    MenuBar {
        Menu("File") {
            Item("New window", onClick = state.openNewWindow)
            Item("Exit", onClick = state.exit)
        }
    }
}


@Composable
fun DosVentanasYCoroutine(perro: () -> Unit) {
    var isPerformingTask by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        // Do some heavy lifting
        delay(2000)
        isPerformingTask = false
    }
    if (isPerformingTask) {
        Window(
            onCloseRequest = perro,
            title = "Window 1"
        )
        {
            Text("Performing some tasks. Please wait!")
        }
    } else {
        Window(
            onCloseRequest = perro,
            title = "Window 2"
        ) {
            Text("Hello, World!")
        }
    }
}

@Composable
fun DosVentanatasCondicioopn(){
    var isOpen by remember { mutableStateOf(true) }
    var isAskingToClose by remember { mutableStateOf(false) }

    if (isOpen) {
        Window(
            onCloseRequest = { isAskingToClose = true },
            title = "Important document"
        ) {
            if (isAskingToClose) {
                DialogWindow(
                    onCloseRequest = { isAskingToClose = false },
                    title = "Close without saving?"
                ) {
                    Button(
                        onClick = { isOpen = false }
                    ) {
                        Text("Yes")
                    }
                }
            }
        }
    }

}