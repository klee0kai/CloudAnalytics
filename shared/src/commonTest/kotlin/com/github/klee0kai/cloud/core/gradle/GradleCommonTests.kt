package com.github.klee0kai.cloud.core.gradle

import com.github.klee0kai.cloud.core.di.DI
import com.github.klee0kai.cloud.core.di.identifier.GradleProjectId
import com.github.klee0kai.cloud.core.utils.runTest
import kotlin.test.Test

class GradleCommonTests {

    @Test
    fun projectInfo() = runTest {
        val theKetGradle = DI.gradleProjectService(GradleProjectId("/home/panda/Documents/AndroidProject/thekey"))
        val info = theKetGradle().info()
        println(info)
    }

}