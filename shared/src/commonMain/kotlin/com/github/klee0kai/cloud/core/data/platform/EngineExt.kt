package com.github.klee0kai.cloud.core.data.platform

import com.russhwolf.settings.Settings
import io.ktor.client.*

expect fun initNetworkEngine(): HttpClient

expect fun initSettingsEngine(): Settings

