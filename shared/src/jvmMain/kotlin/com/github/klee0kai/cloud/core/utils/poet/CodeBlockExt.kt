package com.github.klee0kai.cloud.core.utils.poet

import com.squareup.kotlinpoet.CodeBlock

@PoetDsl
fun codeBlock(
    block: CodeBlock.Builder.() -> Unit,
) = CodeBlock.builder().apply(block).build()


@PoetDsl
fun CodeBlock.Builder.codeFlow(
    controlFlow: String,
    block: CodeBlock.Builder.() -> Unit = {},
) {
    beginControlFlow(controlFlow)
    block()
    endControlFlow()
}