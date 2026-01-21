package com.github.klee0kai.cloud.core.utils

import kotlinx.coroutines.test.runTest as rTest

actual fun runTest(block: suspend () -> Unit) {
    rTest { block() }
}
