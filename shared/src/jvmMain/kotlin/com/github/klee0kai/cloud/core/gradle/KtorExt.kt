@file:OptIn(InternalApi::class)

package com.github.klee0kai.cloud.core.gradle

import aws.smithy.kotlin.runtime.InternalApi
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.receiveAsFlow

suspend inline fun <reified T> CoroutineScope.receiveModel(
    port: Int = 0,
): Pair<Int, Flow<T>> {
    val outputFlow = Channel<T>(Channel.BUFFERED)

    val server = embeddedServer(Netty, port) {
        install(ContentNegotiation) {
            json()
        }
        routing {
            post {
                val payload: T = call.receive()
                outputFlow.send(payload)
                call.respond(
                    status = HttpStatusCode.OK,
                    message = mapOf("status" to "ok")
                )
            }
        }
    }.start(wait = false)
    coroutineContext[Job]!!.invokeOnCompletion {
        server.stop(10, 100)
        outputFlow.close()
    }

    val port = server.engine.resolvedConnectors().first().port
    return port to outputFlow.receiveAsFlow()
}
