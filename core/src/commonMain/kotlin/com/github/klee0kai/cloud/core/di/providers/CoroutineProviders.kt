package com.github.klee0kai.cloud.core.di.providers

import com.github.klee0kai.cloud.core.di.qualifiers.DefaultDispatcher
import com.github.klee0kai.cloud.core.di.qualifiers.MainDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope

interface CoroutineProviders {

    @MainDispatcher
    fun mainDispatcher(): CoroutineDispatcher

    @MainDispatcher
    fun mainThreadScope(): CoroutineScope

    @DefaultDispatcher
    fun defaultDispatcher(): CoroutineDispatcher

    @DefaultDispatcher
    fun defaultThreadScope(): CoroutineScope

}