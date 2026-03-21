package com.github.klee0kai.cloud.core.di.modules

import com.github.klee0kai.cloud.core.data.platform.initNetworkEngine
import com.github.klee0kai.cloud.core.data.platform.initSettingsEngine
import com.github.klee0kai.cloud.core.data.settings.AppLocalSettings
import com.github.klee0kai.stone.annotations.module.Module
import com.github.klee0kai.stone.annotations.module.Provide
import com.russhwolf.settings.Settings
import io.ktor.client.*

@Module
interface DataCoreModule {

    @Provide(cache = Provide.CacheType.Weak)
    fun networkEngine(): HttpClient = initNetworkEngine()

    @Provide(cache = Provide.CacheType.Weak)
    fun settingsEngine(): Settings = initSettingsEngine()

    @Provide(cache = Provide.CacheType.Weak)
    fun appSettings(): AppLocalSettings = AppLocalSettings()

}