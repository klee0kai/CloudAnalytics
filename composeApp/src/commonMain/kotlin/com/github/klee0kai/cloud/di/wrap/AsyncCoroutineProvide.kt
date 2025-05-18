package com.github.klee0kai.cloud.di.wrap

import co.touchlab.kermit.Logger
import com.github.klee0kai.stone.wrappers.Ref
import kotlinx.coroutines.*

class AsyncCoroutineProvide<T>(
    private val provider: suspend () -> T
) {

    constructor(call: Ref<T>) : this(provider = { call.get() })

    @OptIn(DelicateCoroutinesApi::class)
    private val asyncValue =
        GlobalScope.async(Dispatchers.Default + errorHandler) {
            provider.invoke()
        }

    suspend fun get(): T = asyncValue.await()

    suspend operator fun invoke(): T = asyncValue.await()

    fun syncGet() = runBlocking { provider.invoke() }

    companion object {
        private val errorHandler = CoroutineExceptionHandler { _, exception ->
            Logger.a("resolve dep error", exception)
        }
    }

}