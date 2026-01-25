package com.github.klee0kai.cloud.core.utils

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.time.Duration

actual fun runTest(
    timeout: Duration,
    block: suspend () -> Unit,
) {
    GlobalScope.launch {
        block()
    }
}