package com.github.klee0kai.cloud.devkit.theme

import androidx.compose.material3.Shapes
import com.github.klee0kai.cloud.devkit.color.darkCommonColorScheme
import com.github.klee0kai.cloud.devkit.color.lightCommonColorScheme
import com.github.klee0kai.cloud.devkit.typography.regularAppTypeScheme

object DefaultThemes {

    val defThemeIdentifier = ThemeIdentifier.DarkTheme

    val darkTheme = AppTheme(
        colorScheme = darkCommonColorScheme(),
        typeScheme = regularAppTypeScheme(),
        shapes = Shapes(),
    )

    val lightTheme = AppTheme(
        colorScheme = lightCommonColorScheme(),
        typeScheme = regularAppTypeScheme(),
        shapes = Shapes(),
    )


}