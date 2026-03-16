package com.github.klee0kai.cloud.core.utils.poet

import com.squareup.kotlinpoet.*

@PoetDsl
fun genKotlinFile(
    packageName: String,
    fileName: String,
    block: FileSpec.Builder.() -> Unit,
): FileSpec {
    return FileSpec.builder(packageName, fileName).also(block).build()
}


@PoetDsl
fun genKotlinScript(
    fileName: String,
    packageName: String = "",
    block: FileSpec.Builder.() -> Unit,
): FileSpec {
    return FileSpec.scriptBuilder(fileName, packageName = packageName).also(block).build()
}

@PoetDsl
fun FileSpec.Builder.genProperty(
    name: String, type: TypeName, vararg modifiers: KModifier, block: PropertySpec.Builder.() -> Unit = {}
) {
    addProperty(
        PropertySpec.builder(name, type, *modifiers).apply(block).build()
    )
}

@PoetDsl
fun FileSpec.Builder.genClass(
    className: ClassName,
    block: TypeSpec.Builder.() -> Unit = {},
) {
    addType(
        TypeSpec.classBuilder(className).apply(block).build()
    )
}


@PoetDsl
fun FileSpec.Builder.genObject(
    className: ClassName,
    block: TypeSpec.Builder.() -> Unit = {},
) {
    addType(
        TypeSpec.objectBuilder(className).apply(block).build()
    )
}

@PoetDsl
fun FileSpec.Builder.genInterface(
    className: ClassName,
    block: TypeSpec.Builder.() -> Unit = {},
) {
    addType(
        TypeSpec.interfaceBuilder(className).apply(block).build()
    )
}

@PoetDsl
fun FileSpec.Builder.genFun(
    name: String,
    block: FunSpec.Builder.() -> Unit = {},
) {
    addFunction(
        FunSpec.builder(name).apply(block).build()
    )
}


@PoetDsl
fun FileSpec.Builder.codeBlock(
    block: CodeBlock.Builder.() -> Unit = {}
) {
    addCode(
        CodeBlock.builder().apply(block).build()
    )
}

@PoetDsl
fun FileSpec.Builder.codeFlow(
    controlFlow: String,
    block: CodeBlock.Builder.() -> Unit = {},
) {
    addCode(
        CodeBlock.builder().apply {
            beginControlFlow(controlFlow)
            block()
            endControlFlow()
        }.build()
    )
}



