package com.github.klee0kai.cloud.core.kotlin

import ai.koog.utils.io.Closeable
import org.jetbrains.kotlin.com.intellij.psi.PsiManager
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtPsiFactory

interface KotlinPsiService : Closeable {
    val psiFactory: KtPsiFactory

    val psiManager: PsiManager

    fun findKotlinFiles(
        extension: String = "kt",
    ): Sequence<KtFile>

    fun writeToDisk(
        ktFile: KtFile
    )

    fun modifyFiles(
        extension: String = "kt",
        block: (KtFile) -> Unit,
    )


}

fun KotlinPsiService.visitFile(
    extension: String = "kt",
    block: (KtFile) -> Unit,
) = findKotlinFiles(extension).forEach(block)
