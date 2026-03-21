package com.github.klee0kai.cloud.core.utils.gradle

import com.github.klee0kai.cloud.core.di.CoreJvmDI
import com.github.klee0kai.cloud.core.di.identifier.GradleProjectId
import com.github.klee0kai.cloud.core.utils.runTest
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.KtNamedFunction
import org.jetbrains.kotlin.psi.KtPsiFactory
import org.jetbrains.kotlin.psi.KtTreeVisitorVoid
import kotlin.test.Test
import kotlin.time.Duration.Companion.minutes

class GradleCommonTests {

    @Test(timeout = 10_000_000)
    fun projectInfo() = runTest {
        val theKetGradle = CoreJvmDI
            .gradleProjectService(GradleProjectId("/home/panda/Documents/AndroidProject/thekey_community"))

        val info = theKetGradle().info()
        println(info)

    }

    @Test(timeout = 10_000_000)
    fun projectTaskTree() = runTest(timeout = 20.minutes) {
        val theKetGradle = CoreJvmDI
            .gradleProjectService(GradleProjectId("/home/panda/Documents/AndroidProject/thekey_community"))

        val info = theKetGradle().taskTree()
        println(info)
    }


    @Test(timeout = 10_000_000)
    fun modifyFilesTest() = runTest(timeout = 20.minutes) {
        val psi = CoreJvmDI
            .kotlinPsiService(GradleProjectId("/home/panda/Documents/AndroidProject/thekey_community"))
            .invoke()

        psi.modifyFiles { ktFile ->
            val factory = KtPsiFactory(ktFile.project)
            ktFile.accept(object : KtTreeVisitorVoid() {
                override fun visitClass(klass: KtClass) {
                    super.visitClass(klass)
                    if (!klass.isInterface()) return

                    klass.declarations
                        .filterIsInstance<KtNamedFunction>()
                        .forEach { function ->
                            generateKDocForFunction(function, factory)
                        }
                }
            })
        }

    }


    private fun generateKDocForFunction(function: KtNamedFunction, factory: KtPsiFactory) {
        val params = function.valueParameters
        if (params.isEmpty()) return

        if (function.docComment != null) return

        val kdocText = buildString {
            append("/**\n")
            append(" * Auto-generated description for ${function.name}\n")
            params.forEach { param ->
                append(" * @param ${param.name} documentation for parameter\n")
            }
            append(" */")
        }

        val kdoc = factory.createComment(kdocText)

        function.addBefore(kdoc, function.firstChild)
        function.addBefore(factory.createNewLine(), function.firstChild)
    }

}