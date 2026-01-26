package com.github.klee0kai.cloud

//import androidx.compose.desktop.ui.tooling.preview.Preview
//import androidx.compose.runtime.Composable
import androidx.compose.ui.window.MenuBar
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
//import com.github.klee0kai.cloud.devkit.components.grapth.MyGraphViewer

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "CloudAnalytics",
    ) {
        MenuBar {
            Menu(text = "File", mnemonic = 'F') {
                Item(text = "Save", onClick = {})
            }
        }

//        MyGraphViewer()
        App()
    }
}