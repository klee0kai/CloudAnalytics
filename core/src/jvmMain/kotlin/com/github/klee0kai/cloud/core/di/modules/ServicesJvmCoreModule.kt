package com.github.klee0kai.cloud.core.di.modules

import com.github.klee0kai.cloud.core.di.identifier.GradleProjectId
import com.github.klee0kai.cloud.core.gradle.GradleProjectService
import com.github.klee0kai.cloud.core.gradle.GradleProjectServiceImpl
import com.github.klee0kai.cloud.core.kotlin.KotlinPsiService
import com.github.klee0kai.cloud.core.kotlin.KotlinPsiServiceImpl
import com.github.klee0kai.stone.annotations.module.Module
import com.github.klee0kai.stone.annotations.module.Provide

@Module
interface ServicesJvmCoreModule {

    @Provide(cache = Provide.CacheType.Weak)
    fun gradleProjectService(
        gradleProjectId: GradleProjectId,
    ): GradleProjectService = GradleProjectServiceImpl(gradleProjectId)

    @Provide(cache = Provide.CacheType.Weak)
    fun kotlinPsiService(
        gradleProjectId: GradleProjectId,
    ): KotlinPsiService = KotlinPsiServiceImpl(gradleProjectId)

}