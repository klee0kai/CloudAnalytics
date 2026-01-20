@file:OptIn(ExperimentalTime::class)

package com.github.klee0kai.cloud.utils.coroutine

import kotlinx.coroutines.*
import kotlin.time.Clock
import kotlin.time.Duration
import kotlin.time.ExperimentalTime

fun emptyJob(): Job = Job().also { it.complete() }

fun <T> completeAsync(result: T): Deferred<T> = CompletableDeferred(value = result)

suspend fun <T> minDuration(duration: Duration, block: suspend () -> T): T {
    val start = Clock.System.now()
    val r = block.invoke()
    val left = duration - (Clock.System.now() - start)
    if (left.isPositive()) delay(left)
    return r
}

suspend inline fun <reified T> Deferred<T>.awaitSec(): T? = withTimeoutOrNull(1000) {
    await()
}
