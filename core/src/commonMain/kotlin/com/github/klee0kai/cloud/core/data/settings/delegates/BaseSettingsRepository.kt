package com.github.klee0kai.cloud.core.data.settings.delegates

import com.github.klee0kai.cloud.core.di.CoreComponentDI
import com.tradingview.mountebank.compose.data.settings.delegates.IntNoteDelegate
import com.tradingview.mountebank.compose.data.settings.delegates.LongNoteDelegate
import kotlinx.serialization.json.Json

open class BaseSettingsRepository(
    private val isFake: Boolean = false,
) {

    protected val settingsEngine = CoreComponentDI.settingsEngine()
    protected val scope = CoreComponentDI.defaultThreadScope()
    val jsonEngine = Json

    protected fun stringDelegate(
        settingId: String,
        isFake: Boolean = false,
        defaultValue: () -> String
    ) = StringNoteDelegate(
        settingsEngine,
        scope,
        settingId,
        isFake || this.isFake,
        defaultValue,
    )

    protected fun intDelegate(
        settingId: String,
        isFake: Boolean = false,
        defaultValue: () -> Int
    ) = IntNoteDelegate(
        settingsEngine,
        scope,
        settingId,
        isFake || this.isFake,
        defaultValue,
    )

    protected fun longDelegate(
        settingId: String,
        isFake: Boolean = false,
        defaultValue: () -> Long
    ) = LongNoteDelegate(
        settingsEngine,
        scope,
        settingId,
        isFake || this.isFake,
        defaultValue
    )

    protected fun booleanDelegate(
        settingId: String,
        isFake: Boolean = false,
        defaultValue: () -> Boolean
    ) = BooleanNoteDelegate(
        settingsEngine,
        scope,
        settingId,
        isFake || this.isFake,
        defaultValue
    )

    protected inline fun <reified T> delegate(
        settingId: String,
        isFake: Boolean = false,
        noinline getTransform: ((String) -> T)? = null,
        noinline setTransform: ((T) -> String)? = null,
        noinline defaultValue: () -> T,
    ) = SettingsNoteDelegate<T>(
        settingsEngine, scope, settingId, isFake,
        defaultValue,
        getTransform = getTransform ?: {
            if (it.isEmpty()) {
                defaultValue()
            } else {
                runCatching<T> {
                    jsonEngine.decodeFromString(it)
                }.getOrNull()
                    ?: defaultValue()
            }
        },
        setTransform = setTransform ?: {
            runCatching { jsonEngine.encodeToString(it) }
                .getOrNull() ?: ""
        }
    )


}