package com.github.klee0kai.cloud.devkit.theme

import com.github.klee0kai.cloud.devkit.theme.DefaultThemes.defThemeIdentifier
import kotlinx.coroutines.flow.MutableStateFlow

class AppThemeManagerImpl : AppThemeManager {

//    private val scope = CoreDI.defaultThreadScope()

    private val modifiers = mutableListOf<Pair<String, AppTheme.() -> AppTheme>>()

    override val themeIdentifier = MutableStateFlow<ThemeIdentifier>(defThemeIdentifier)

//    override val theme = themeIdentifier.map {
//        var theme = it.toTheme()
//        scope.mutex.withLock {
//            modifiers.forEach { (_, modifier) ->
//                theme = modifier.invoke(theme)
//            }
//        }
//        theme
//    }.flowOn(CoreDI.defaultDispatcher())
//        .touchable()


    override fun setTheme(themeIdentifier: ThemeIdentifier) {
        this@AppThemeManagerImpl.themeIdentifier.value = themeIdentifier
    }

    override fun modify(id: String, modifier: AppTheme.() -> AppTheme) {
//        scope.launchSafe {
//            modifiers.removeIf { it.first == id }
//            modifiers.add(id to modifier)
//            theme.touch(Unit)
//        }
    }

}

fun ThemeIdentifier.toTheme(): AppTheme = when (this) {
    ThemeIdentifier.DarkTheme -> DefaultThemes.darkTheme
    ThemeIdentifier.LightTheme -> DefaultThemes.lightTheme
}