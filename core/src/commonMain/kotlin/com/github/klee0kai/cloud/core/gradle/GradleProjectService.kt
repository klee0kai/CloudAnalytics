package com.github.klee0kai.cloud.core.gradle

import com.github.klee0kai.cloud.core.gradle.model.ProjectInfo
import com.github.klee0kai.cloud.core.gradle.model.TaskTreeResults

interface GradleProjectService {

    suspend fun info(): Result<ProjectInfo>

    suspend fun taskTree(): Result<TaskTreeResults.ScanResult>

}
