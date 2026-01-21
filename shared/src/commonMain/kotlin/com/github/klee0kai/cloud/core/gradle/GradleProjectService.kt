package com.github.klee0kai.cloud.core.gradle

import com.github.klee0kai.cloud.core.gradle.model.ProjectInfo

interface GradleProjectService {

    suspend fun info(): ProjectInfo

}
