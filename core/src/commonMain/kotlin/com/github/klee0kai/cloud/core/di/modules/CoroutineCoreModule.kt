package com.github.klee0kai.cloud.core.di.modules

import com.github.klee0kai.cloud.core.di.qualifiers.DefaultDispatcher
import com.github.klee0kai.cloud.core.di.qualifiers.MainDispatcher
import com.github.klee0kai.stone.annotations.module.Module
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

@Module
open class CoroutineCoreModule {

    @MainDispatcher
    open fun mainDispatcher(): CoroutineDispatcher = Dispatchers.Main.immediate

    @DefaultDispatcher
    open fun defaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

    @MainDispatcher
    open fun mainThreadScope(
        @MainDispatcher
        dispatcher: CoroutineDispatcher,
    ): CoroutineScope = CoroutineScope(dispatcher + SupervisorJob())

    @DefaultDispatcher
    open fun defaultThreadScope(
        @DefaultDispatcher
        dispatcher: CoroutineDispatcher,
    ): CoroutineScope = CoroutineScope(dispatcher + SupervisorJob())

}
