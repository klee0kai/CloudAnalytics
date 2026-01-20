package com.github.klee0kai.cloud.utils.common

import kotlinx.atomicfu.atomic

object Dummy {

    private val dummyIdCounter = atomic(0L)

    val dummyId get() = dummyIdCounter.incrementAndGet()

}