package com.github.klee0kai.cloud.core.di.dependencies

import com.github.klee0kai.cloud.core.di.identifier.GradleProjectId
import com.github.klee0kai.cloud.core.gradle.GradleProjectService
import com.github.klee0kai.stone.wrappers.AsyncCoroutineProvide

interface ServicesDependencies {

    fun gradleProjectService(
        gradleProjectId: GradleProjectId,
    ): AsyncCoroutineProvide<GradleProjectService>

}