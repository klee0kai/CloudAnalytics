package com.github.klee0kai.cloud.devkit

import androidx.compose.ui.unit.LayoutDirection

data class ComposeConfig(
    val isDebug: Boolean = false,
    val isViewEditMode: Boolean = false,
    val layoutDirection: LayoutDirection = LayoutDirection.Ltr,
)