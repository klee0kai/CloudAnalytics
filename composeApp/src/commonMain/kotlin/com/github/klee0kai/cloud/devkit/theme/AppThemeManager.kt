package com.github.klee0kai.cloud.devkit.theme

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

interface AppThemeManager {

    val theme: Flow<AppTheme> get() = emptyFlow()

    val themeIdentifier: Flow<ThemeIdentifier> get() = emptyFlow()

    /**
     * set theme
     */
    fun setTheme(themeIdentifier: ThemeIdentifier) = Unit

    /**
     * when changing the theme, theme modifiers are applied by their identifiers
     */
    fun modify(id: String, modifier: AppTheme.() -> AppTheme = { this }) = Unit

}