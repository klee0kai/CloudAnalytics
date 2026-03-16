package com.github.klee0kai.cloud.core.di.modules

import com.github.klee0kai.cloud.core.di.identifier.GradleProjectId
import com.github.klee0kai.cloud.core.gradle.GradleProjectServiceImpl

open class ServicesModuleJvm : ServicesModule {

    override fun gradleProjectService(
        gradleProjectId: GradleProjectId,
    ) = GradleProjectServiceImpl(gradleProjectId)

}