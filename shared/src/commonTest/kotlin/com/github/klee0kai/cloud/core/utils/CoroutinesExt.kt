package com.github.klee0kai.cloud.core.utils

import kotlin.time.Duration
import kotlin.time.Duration.Companion.minutes

expect fun runTest(
    timeout: Duration = 1.minutes,
    block: suspend () -> Unit,
)