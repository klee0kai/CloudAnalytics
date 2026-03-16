package com.github.klee0kai.cloud.core.data.settings.delegates

import com.github.klee0kai.stone.wrappers.AsyncCoroutineProvide
import com.russhwolf.settings.Settings
import kotlinx.coroutines.CoroutineScope

class BooleanNoteDelegate(
    settingsEngine: AsyncCoroutineProvide<Settings>,
    scope: CoroutineScope,
    settingId: String,
    isFake: Boolean = false,
    defaultValue: () -> Boolean,
) : SettingsNoteDelegate<Boolean>(
    settingsEngine, scope, settingId, isFake,
    defaultValue,
    getTransform = { it.toBoolean() },
    setTransform = { it.toString() }
)