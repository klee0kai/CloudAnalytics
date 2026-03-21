package com.github.klee0kai.cloud.core.di.providers

import com.github.klee0kai.cloud.core.di.identifier.GradleProjectId
import com.github.klee0kai.cloud.core.gradle.GradleProjectService
import com.github.klee0kai.stone.wrappers.AsyncCoroutineProvide

interface ServicesJvmCoreProvider {

    fun gradleProjectService(
        gradleProjectId: GradleProjectId,
    ): AsyncCoroutineProvide<GradleProjectService>

}