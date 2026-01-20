package com.github.klee0kai.cloud.utils.coroutine

import kotlinx.atomicfu.atomic
import kotlinx.atomicfu.getAndUpdate
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


interface TouchableFlow<out T, in Arg> : Flow<T> {

    /**
     * update data in cold flow
     */
    fun touch(arg: Arg)

}

fun <T> TouchableFlow<T, Unit>.touch() = touch(Unit)

fun <T> Flow<T>.touchable(): TouchableFlow<T, Unit> {
    val originFlow = this
    val ticker = MutableSharedFlow<Unit>(replay = 1)
    val touchBody = channelFlow {
        val lastJob = atomic<Job?>(null)
        ticker.onTicks {
            lastJob.getAndUpdate { last ->
                last?.cancel()
                launch {
                    originFlow.collect {
                        send(it)
                    }
                }
            }
        }
    }
    return object : TouchableFlow<T, Unit>, Flow<T> by touchBody {

        override fun touch(arg: Unit) {
            ticker.tryEmit(arg)
        }

    }
}

/**
 * restartable flow with arg
 * be careful. flow restart for each subscriber
 */
fun <T, Arg> touchableFlow(
    init: Arg,
    block: suspend FlowCollector<T>.(arg: Arg) -> Unit,
): TouchableFlow<T, Arg> {
    val ticker = MutableSharedFlow<Arg>()
    val lastJob = atomic<Job?>(null)
    val touchBody = channelFlow {
        ticker.onTicks(init) { arg ->
            lastJob.getAndUpdate { last ->
                last?.cancel()
                launch {
                    flow<T> {
                        block(arg)
                    }.collect {
                        send(it)
                    }
                }
            }
        }
    }
    return object : TouchableFlow<T, Arg>, Flow<T> by touchBody {

        override fun touch(arg: Arg) {
            ticker.tryEmit(arg)
        }

    }
}