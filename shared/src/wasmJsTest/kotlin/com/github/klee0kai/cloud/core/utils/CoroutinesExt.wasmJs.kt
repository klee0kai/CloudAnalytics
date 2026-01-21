package com.github.klee0kai.cloud.core.utils

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

actual fun runTest(block: suspend () -> Unit) {
    GlobalScope.launch {
        block()
    }
}