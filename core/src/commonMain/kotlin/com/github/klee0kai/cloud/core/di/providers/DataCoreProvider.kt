package com.github.klee0kai.cloud.core.di.providers

import com.github.klee0kai.cloud.core.data.settings.AppLocalSettings
import com.github.klee0kai.stone.wrappers.AsyncCoroutineProvide
import com.russhwolf.settings.Settings
import io.ktor.client.*

interface DataCoreProvider {

    fun networkEngine(): AsyncCoroutineProvide<HttpClient>

    fun settingsEngine(): AsyncCoroutineProvide<Settings>

    fun appSettings(): AsyncCoroutineProvide<AppLocalSettings>

}