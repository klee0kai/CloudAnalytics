package com.github.klee0kai.cloud

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import kotlinx.browser.document
import kotlinx.browser.window

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    val path = window.location.pathname.dropWhile { it == '/' }
    println("path: $path")
    ComposeViewport(document.body!!) {
        when (path) {
            "privacy" -> App()
            else -> App()
        }
    }
}