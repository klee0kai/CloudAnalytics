@file:OptIn(K1Deprecation::class)

package com.github.klee0kai.cloud.core.kotlin

import com.github.klee0kai.cloud.core.di.identifier.GradleProjectId
import com.github.klee0kai.cloud.core.kotlin.mock.PomModelMock
import com.github.klee0kai.cloud.core.kotlin.psi.WritableVirtualFileWrapper
import com.pinterest.ktlint.rule.engine.api.Code
import com.pinterest.ktlint.rule.engine.api.KtLintRuleEngine
import com.pinterest.ktlint.ruleset.standard.StandardRuleSetProvider
import org.jetbrains.kotlin.K1Deprecation
import org.jetbrains.kotlin.cli.jvm.compiler.EnvironmentConfigFiles
import org.jetbrains.kotlin.cli.jvm.compiler.KotlinCoreEnvironment
import org.jetbrains.kotlin.com.intellij.core.CoreApplicationEnvironment
import org.jetbrains.kotlin.com.intellij.mock.MockProject
import org.jetbrains.kotlin.com.intellij.openapi.extensions.ExtensionPointName
import org.jetbrains.kotlin.com.intellij.openapi.extensions.Extensions
import org.jetbrains.kotlin.com.intellij.openapi.util.Disposer
import org.jetbrains.kotlin.com.intellij.openapi.vfs.StandardFileSystems
import org.jetbrains.kotlin.com.intellij.pom.PomModel
import org.jetbrains.kotlin.com.intellij.pom.tree.TreeAspect
import org.jetbrains.kotlin.com.intellij.psi.PsiManager
import org.jetbrains.kotlin.com.intellij.psi.impl.source.tree.TreeCopyHandler
import org.jetbrains.kotlin.config.CompilerConfiguration
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtPsiFactory
import java.io.File

class KotlinPsiServiceImpl(
    val gradleProjectId: GradleProjectId,
) : KotlinPsiService {

    val projectDir = File(gradleProjectId.projectPath)

    val disposable = Disposer.newDisposable()

    val env: KotlinCoreEnvironment = KotlinCoreEnvironment.createForProduction(
        projectDisposable = disposable,
        configuration = CompilerConfiguration(),
        configFiles = EnvironmentConfigFiles.JVM_CONFIG_FILES
    )


    val localFileSystem = StandardFileSystems.local()

    override val psiFactory = KtPsiFactory(env.project)

    override val psiManager: PsiManager = PsiManager.getInstance(env.project)

    private val ktLintRuleEngine = KtLintRuleEngine(
        StandardRuleSetProvider().getRuleProviders(),
    )

    val project = env.project as MockProject


    init {
        val area = Extensions.getRootArea()
        val epName = ExtensionPointName.create<TreeCopyHandler>("org.jetbrains.kotlin.com.intellij.treeCopyHandler")

        if (!area.hasExtensionPoint(epName)) {
            CoreApplicationEnvironment.registerExtensionPoint(area, epName, TreeCopyHandler::class.java)
        }


        if (project.getComponent(TreeAspect::class.java) == null) {
            project.registerService(
                TreeAspect::class.java,
                TreeAspect()
            )
        }

        project.registerService(
            PomModel::class.java,
            PomModelMock(project),
        )

    }

    override fun findKotlinFiles(
        extension: String,
    ) = sequence<KtFile> {
        projectDir.walkTopDown()
            .filter {
                it.extension.lowercase() == extension
            }
            .mapNotNull { file ->
                val virtualFile = localFileSystem
                    .findFileByPath(file.absolutePath)
                    ?.let { WritableVirtualFileWrapper(it) }
                    ?: return@mapNotNull null

                val ktFile = psiManager.findFile(virtualFile) as? KtFile
                    ?: return@mapNotNull null

                ktFile
            }.let {
                yieldAll(it)
            }
    }

    override fun writeToDisk(
        ktFile: KtFile
    ) {
        val fileOnDisk = File(ktFile.virtualFilePath)
        val formattedCode = ktLintRuleEngine.format(Code.fromSnippet(ktFile.text))
        fileOnDisk.writeText(formattedCode)
    }


    override fun modifyFiles(
        extension: String,
        block: (KtFile) -> Unit,
    ) {

        findKotlinFiles(extension).forEach { ktFile ->
            val old = ktFile.text.trim()

            block(ktFile)
            val new = ktFile.text.trim()

            if (old != new) {
                writeToDisk(ktFile)
            }
        }
    }

    override suspend fun close() {
        disposable.dispose()
    }


}