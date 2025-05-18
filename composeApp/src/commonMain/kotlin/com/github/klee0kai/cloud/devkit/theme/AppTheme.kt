package com.github.klee0kai.cloud.devkit.theme

import androidx.compose.material3.Shapes
import androidx.compose.runtime.Stable
import com.github.klee0kai.cloud.devkit.color.CommonColorScheme
import com.github.klee0kai.cloud.devkit.typography.TypeScheme

@Stable
data class AppTheme(
    val colorScheme: CommonColorScheme,
    val typeScheme: TypeScheme,
    val shapes: Shapes,
)