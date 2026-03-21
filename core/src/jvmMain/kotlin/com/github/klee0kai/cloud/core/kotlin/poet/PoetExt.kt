package com.github.klee0kai.cloud.core.kotlin.poet

import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.ParameterSpec
import com.squareup.kotlinpoet.PropertySpec

@DslMarker
annotation class PoetDsl

fun PropertySpec.asParameter(): ParameterSpec = ParameterSpec.builder(name, type).build()

fun PropertySpec.Builder.initFromConstructor(): PropertySpec.Builder = apply { initializer(build().name) }

fun Collection<CodeBlock>.toCodeBlock(): CodeBlock {
    val blocks = this
    return CodeBlock.builder().apply {
        blocks.forEach {
            add(it)
        }
    }.build()
}
