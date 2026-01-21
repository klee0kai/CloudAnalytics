package com.github.klee0kai.cloud.core.gradle.model

import org.gradle.tooling.model.GradleProject

fun GradleProject.toProjectInfo(
): ProjectInfo = ProjectInfo(
    name = this.name,
    projectPath = path.toString(),
    children = children.map { it.toProjectInfo() }
)
