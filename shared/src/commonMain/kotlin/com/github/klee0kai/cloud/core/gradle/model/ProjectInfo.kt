package com.github.klee0kai.cloud.core.gradle.model

data class ProjectInfo(
    val name: String = "",
    val projectPath: String = "",
    val children: List<ProjectInfo> = emptyList(),
)
