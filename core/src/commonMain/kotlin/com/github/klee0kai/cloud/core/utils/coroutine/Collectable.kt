package com.github.klee0kai.cloud.core.utils.coroutine

interface Collectable<T> {

    suspend fun await(): T

    suspend fun cancelAndGet(): T

}

