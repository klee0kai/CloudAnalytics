package com.github.klee0kai.cloud.core.gradle

import com.github.klee0kai.cloud.core.di.DI
import com.github.klee0kai.cloud.core.di.identifier.GradleProjectId
import com.github.klee0kai.cloud.core.utils.runTest
import kotlin.test.Test
import kotlin.time.Duration.Companion.minutes

class GradleCommonTests {

    @Test
    fun projectInfo() = runTest {
        val theKetGradle = DI.gradleProjectService(GradleProjectId("/home/panda/Documents/AndroidProject/thekey_community"))
        val info = theKetGradle().info()
        println(info)
    }

    @Test
    fun projectTaskTree() = runTest(timeout = 20.minutes) {
        val theKetGradle = DI.gradleProjectService(GradleProjectId("/home/panda/Documents/AndroidProject/thekey_community"))
        val info = theKetGradle().taskTree()
        println(info)
    }

}