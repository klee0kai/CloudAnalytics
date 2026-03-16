package com.tradingview.mountebank.compose.data.settings.delegates

import com.github.klee0kai.cloud.core.data.settings.delegates.SettingsNoteDelegate
import com.github.klee0kai.stone.wrappers.AsyncCoroutineProvide
import com.russhwolf.settings.Settings
import kotlinx.coroutines.CoroutineScope

class LongNoteDelegate(
    settingsEngine: AsyncCoroutineProvide<Settings>,
    scope: CoroutineScope,
    settingId: String,
    isFake: Boolean = false,
    defaultValue: () -> Long,
) : SettingsNoteDelegate<Long>(
    settingsEngine, scope, settingId, isFake,
    defaultValue,
    getTransform = { it.toLongOrNull() ?: 0L },
    setTransform = { it.toString() }
)