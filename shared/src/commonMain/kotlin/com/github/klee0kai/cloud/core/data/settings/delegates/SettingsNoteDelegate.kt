package com.github.klee0kai.cloud.core.data.settings.delegates

import com.github.klee0kai.stone.wrappers.AsyncCoroutineProvide
import com.russhwolf.settings.Settings
import com.russhwolf.settings.contains
import com.russhwolf.settings.set
import com.github.klee0kai.cloud.core.utils.coroutine.lazyStateFlow
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlin.time.Duration

private val NotInited = object {}

open class SettingsNoteDelegate<T>(
    private val settingsEngine: AsyncCoroutineProvide<Settings>,
    private val scope: CoroutineScope,
    private val settingId: String,
    private val isFake: Boolean = false,
    private val defaultValue: () -> T,
    private val getTransform: (String) -> T,
    private val setTransform: (T) -> String,
) {

    private val lazyState = lazyStateFlow<Any?, Unit>(
        init = NotInited,
        defaultArg = Unit,
        scope = scope,
    ) {
        if (isFake) {
            value = defaultValue()
        } else {
            value = settingsEngine().getStringOrNull(settingId)
                ?.let { getTransform(it) }
                ?: defaultValue()
        }
    }

    open val flow = lazyState.filter { it != NotInited }.map { it as T }

    open fun set(value: T): Job = scope.launch {
        if (isFake) return@launch
        settingsEngine()[settingId] = setTransform(value)
        lazyState.value = value
    }

    open fun delete(): Job = scope.launch {
        if (isFake) return@launch
        settingsEngine().remove(settingId)
        lazyState.value = defaultValue()
    }

    open fun get(): Deferred<T> = scope.async { flow.first() }

    open fun isDefault(): Deferred<Boolean> = scope.async {
        return@async get().await() == defaultValue
    }

    open fun isNotSet(): Deferred<Boolean> = scope.async {
        return@async !settingsEngine().contains(settingId)
    }

    open suspend operator fun invoke(ttl: Duration? = null): T {
        if (isFake) return flow.first()
        return settingsEngine().getStringOrNull(settingId)
            ?.let { getTransform(it) }
            ?: defaultValue()
    }

}