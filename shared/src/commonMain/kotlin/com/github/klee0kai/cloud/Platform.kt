package com.github.klee0kai.cloud

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform