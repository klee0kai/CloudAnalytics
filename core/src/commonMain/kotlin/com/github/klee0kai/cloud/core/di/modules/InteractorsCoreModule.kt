package com.github.klee0kai.cloud.core.di.modules

import com.github.klee0kai.cloud.core.di.identifier.GradleProjectId
import com.github.klee0kai.cloud.core.gradle.GradleProjectInteractor
import com.github.klee0kai.stone.annotations.module.Module

@Module
interface InteractorsCoreModule {

    suspend fun gradleProjectInteractor(
        gradleProjectId: GradleProjectId,
    ): GradleProjectInteractor = error("need to implement")

}