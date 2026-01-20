package com.github.klee0kai.cloud.utils.coroutine

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.ProducerScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.runningFold
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.takeWhile
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

fun <T> singleEventFlow(
    coroutineContext: CoroutineContext = EmptyCoroutineContext,
    block: suspend CoroutineScope.() -> T
) = channelFlow {
    val result = block()
    send(result)
}.flowOn(coroutineContext)

suspend fun Flow<Unit>.onTicks(block: suspend () -> Unit) {
    merge(this, flowOf(Unit)).collect {
        block()
    }
}

suspend fun <Arg> Flow<Arg>.onTicks(init: Arg, block: suspend (arg: Arg) -> Unit) {
    merge(flowOf(init), this).collectLatest {
        block(it)
    }
}

inline fun <reified T> Flow<T>.changeFilter(
    crossinline filter: suspend (old: T?, new: T) -> Boolean
): Flow<T> = runningFold(arrayOf()) { accumulator: Array<T>, value: T ->
    if (accumulator.isEmpty()) {
        arrayOf(value)
    } else {
        arrayOf(accumulator.last(), value)
    }
}.filter { array ->
    when (array.size) {
        0 -> false
        1 -> filter.invoke(null, array.last())
        else -> filter.invoke(array.first(), array.last())
    }
}.map { it.last() }


suspend inline fun <reified T> Flow<T>.await(timeout: Long): T? =
    withTimeout(timeout) { firstOrNull() }

suspend inline fun <reified T> Flow<T>.awaitSec(): T? = await(1000)

fun <T> Flow<T>.collectTo(consumer: ProducerScope<T>) {
    consumer.launch {
        collect { consumer.channel.send(it) }
    }
}