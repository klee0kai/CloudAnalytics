package com.github.klee0kai.cloud

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.net.URL

private val contextClLoader by lazy {
    Thread.currentThread().getContextClassLoader()
}

fun findResourceFromWasmArtefacts(fileName: String): URL? {
    return contextClLoader.getResources(fileName).toList()
        .firstOrNull { it.path.contains(Regex("wasm-artifacts-.*-prod.jar!")) }
}

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondBytes(
                bytes = findResourceFromWasmArtefacts("index.html")!!.openStream().readBytes(),
            )
        }

        get("/privacy") {
            call.respondBytes(
                bytes = findResourceFromWasmArtefacts("index.html")!!.openStream().readBytes(),
            )
        }


        get("{...}") {
            val path = call.request.path().dropWhile { it == '/' }

            val contentType = when {
                path.endsWith(".wasm") -> ContentType.parse("application/wasm")
                else -> null
            }
            call.respondBytes(
                bytes = findResourceFromWasmArtefacts(path)!!.openStream().readBytes(),
                contentType = contentType,
            )
        }
    }
}
