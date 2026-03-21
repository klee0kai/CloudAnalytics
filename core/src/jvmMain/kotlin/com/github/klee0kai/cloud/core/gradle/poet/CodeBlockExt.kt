package com.github.klee0kai.cloud.core.gradle.poet

import com.squareup.kotlinpoet.CodeBlock

@PoetDsl
fun codeBlock(
    block: CodeBlock.Builder.() -> Unit,
) = CodeBlock.builder().apply(block).build()