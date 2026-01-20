package com.github.klee0kai.cloud

actual fun getPlatform(): Platform = object : Platform {
    override val name: String = "wasm"
}