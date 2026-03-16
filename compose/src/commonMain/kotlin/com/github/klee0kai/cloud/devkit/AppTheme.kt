package com.github.klee0kai.cloud.devkit

import androidx.compose.runtime.compositionLocalOf
import com.github.klee0kai.cloud.devkit.color.CommonColorScheme
import com.github.klee0kai.cloud.devkit.theme.AppTheme

//val LocalRouter = compositionLocalOf<AppRouter> { error("no router") }
val LocalColorScheme = compositionLocalOf<CommonColorScheme> { error("no color scheme") }
val LocalTheme = compositionLocalOf<AppTheme> { error("no theme") }
val LocalComposeConfig = compositionLocalOf<ComposeConfig> { ComposeConfig() }
//
//@Composable
//fun AppTheme(
//    modifier: Modifier = Modifier,
//    theme: AppTheme? = null,
//    composeConfig: ComposeConfig? = null,
//    content: @Composable () -> Unit,
//) {
////    LocalConfiguration.current
////    CoreDI.ctx(LocalContext.current)
////    val view = LocalView.current
//
////    val isEditMode = view.isInEditMode || LocalInspectionMode.current || isDebugInspectorInfoEnabled
//    val themeManager = CoreDI.themeManager(activityIdentifier)
////    val router = CoreDI.router(activityIdentifier)
//    val themeManagerState by themeManager.theme
//        .collectAsState(key = Unit, initial = theme ?: DefaultThemes.defThemeIdentifier.toTheme())
//    val targetAppTheme = theme ?: themeManagerState
//    val colorScheme by animateColorAsState(targetAppTheme.colorScheme)
//    val appTheme by rememberDerivedStateOf { targetAppTheme.copy(colorScheme = colorScheme) }
//
//    remember {
//        CoreDI.updateConfig {
//            copy(isViewEditMode = isEditMode)
//        }
//    }
//
//    CompositionLocalProvider(
//        LocalTheme provides appTheme,
////        LocalRouter provides router,
////        LocalActivityIdentifier provides activityIdentifier,
////        LocalShimmerTheme provides shimmer,
//        LocalColorScheme provides appTheme.colorScheme,
//        LocalComposeConfig provides (composeConfig ?: LocalComposeConfig.current),
//    ) {
//        MaterialTheme(
//            colorScheme = appTheme.colorScheme.androidColorScheme,
//            typography = appTheme.typeScheme.typography,
//            shapes = appTheme.shapes,
//        ) {
//            Surface(
//                modifier = modifier,
//                color = appTheme.colorScheme.windowBackgroundColor,
//                contentColor = appTheme.colorScheme.androidColorScheme.onBackground,
//            ) {
//                content.invoke()
//            }
//        }
//    }
//}