package com.github.klee0kai.cloud.navigation

import androidx.compose.runtime.Stable

@Stable
interface Destination {
    val screenName: String get() = "none"
}

