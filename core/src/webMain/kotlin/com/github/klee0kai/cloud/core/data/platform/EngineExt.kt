package com.github.klee0kai.cloud.core.data.platform

import com.russhwolf.settings.Settings
import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.websocket.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

actual fun initNetworkEngine(

): HttpClient = HttpClient {
    install(WebSockets)
    install(ContentNegotiation) {
        json(
            Json {
                ignoreUnknownKeys = true
            }
        )
    }
}

actual fun initSettingsEngine(): Settings = Settings()
