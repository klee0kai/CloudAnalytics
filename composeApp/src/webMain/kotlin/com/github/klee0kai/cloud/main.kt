package com.github.klee0kai.cloud

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
//    val path = window.location.pathname.dropWhile { it == '/' }
    ComposeViewport {
        App()
//        when (path) {
//            "privacy" -> App()
//            else -> App()
//        }
    }
}