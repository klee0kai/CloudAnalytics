package com.github.klee0kai.cloud.core.utils

import kotlin.time.Duration
import kotlinx.coroutines.test.runTest as rTest

actual fun runTest(
    timeout: Duration,
    block: suspend () -> Unit,
) {
    rTest(
        timeout = timeout
    ) { block() }
}
