package com.github.klee0kai.cloud

import io.ktor.server.application.*
//import io.ktor.server.plugins.openapi.*
import io.ktor.server.routing.*

fun Application.configureHTTP() {
    routing {
//        openAPI(path = "openapi")
    }
}
