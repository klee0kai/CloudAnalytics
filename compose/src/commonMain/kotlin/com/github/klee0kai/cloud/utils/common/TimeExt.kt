package com.github.klee0kai.cloud.utils.common

import kotlin.time.Duration.Companion.days

val Int.months get() = 31.days * this

val Int.years get() = 365.days * this