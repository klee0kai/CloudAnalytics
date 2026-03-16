package com.github.klee0kai.cloud.devkit.debug

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalLayoutDirection
import com.github.klee0kai.cloud.devkit.ComposeConfig
import com.github.klee0kai.cloud.devkit.LocalComposeConfig
import com.github.klee0kai.cloud.devkit.debug.annotations.DebugOnly


@DebugOnly
@Composable
fun DebugScreenPreview(
    composeConfig: ComposeConfig? = null,
    content: @Composable () -> Unit
) {
    DebugContentPreview(
        composeConfig = composeConfig,
    ) {
        content()
    }
}

@DebugOnly
@Composable
fun DebugContentPreview(
    composeConfig: ComposeConfig? = null,
    content: @Composable () -> Unit
) {
    val realComposeConfig = composeConfig ?: LocalComposeConfig.current

    CompositionLocalProvider(
        LocalLayoutDirection provides realComposeConfig.layoutDirection,
    ) {
        content()
    }
}