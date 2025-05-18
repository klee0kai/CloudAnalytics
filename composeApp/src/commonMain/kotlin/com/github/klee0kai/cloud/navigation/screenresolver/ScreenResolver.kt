package com.github.klee0kai.cloud.navigation.screenresolver

import androidx.compose.runtime.Composable
import com.github.klee0kai.cloud.navigation.Destination

interface ScreenResolver {

    @Composable
    fun screenOf(destination: Destination) = Unit

}