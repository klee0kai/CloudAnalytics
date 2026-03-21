package com.github.klee0kai.cloud.core.data.settings

import com.github.klee0kai.cloud.core.data.settings.delegates.BaseSettingsRepository

class AppLocalSettings : BaseSettingsRepository(isFake = false) {

    val remoteServerAddress = stringDelegate("remote_server_address") { "http://localhost:8080" }


}