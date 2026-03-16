package com.github.klee0kai.cloud.core.data.platform

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.websocket.*
import io.ktor.network.tls.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import java.security.cert.X509Certificate
import javax.net.ssl.X509TrustManager


actual fun initNetworkEngine(

): HttpClient = HttpClient(CIO) {
    install(WebSockets)
    engine {
        https {
            ignoreCert()
        }
    }


    install(ContentNegotiation) {
        json(
            Json {
                ignoreUnknownKeys = true
            }
        )
    }
}


fun TLSConfigBuilder.ignoreCert() {
    trustManager = object : X509TrustManager {
        override fun checkClientTrusted(
            chain: Array<out X509Certificate>?,
            authType: String?
        ) {
        }

        override fun checkServerTrusted(
            chain: Array<out X509Certificate>?,
            authType: String?
        ) {
        }

        override fun getAcceptedIssuers(): Array<X509Certificate> = emptyArray()
    }
}