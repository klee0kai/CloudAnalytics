package com.github.klee0kai.cloud.core.di.modules

import com.github.klee0kai.cloud.core.di.identifier.GradleProjectId
import com.github.klee0kai.cloud.core.gradle.GradleProjectService
import com.github.klee0kai.stone.annotations.module.Module

@Module
interface ServicesModule {

    fun gradleProjectService(
        gradleProjectId: GradleProjectId,
    ): GradleProjectService = error("need to implement")


}