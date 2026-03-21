package com.github.klee0kai.cloud.core.utils.gradle

import com.github.klee0kai.cloud.core.di.CoreJvmDI
import com.github.klee0kai.cloud.core.di.identifier.GradleProjectId
import com.github.klee0kai.cloud.core.utils.runTest
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

}