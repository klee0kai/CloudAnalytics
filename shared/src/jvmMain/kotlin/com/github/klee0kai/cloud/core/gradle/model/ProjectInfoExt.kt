package com.github.klee0kai.cloud.core.gradle.model

import com.github.klee0kai.tasktree.output.ProjectNode
import com.github.klee0kai.tasktree.output.TaskNode
import com.github.klee0kai.tasktree.output.TaskTreeResult
import org.gradle.tooling.model.GradleProject

fun GradleProject.toProjectInfo(
): ProjectInfo = ProjectInfo(
    name = this.name,
    projectPath = path.toString(),
    children = children.map { it.toProjectInfo() }
)

fun TaskTreeResult.toDomainModel(
) = TaskTreeResults.ScanResult(
    projects = projects.map { it.toDomainModel() },
    tasks = tasks.map { it.toDomainModel() },
)

fun TaskNode.toDomainModel(

) = TaskTreeResults.TaskNode(
    path = path,
    name = name,
    className = className,
    classLoader = classLoader,
    projectName = projectName,
    group = group,
    description = description,
    termDir = termDir,
    dependencies = dependencies,
    sourceFiles = sourceFiles,
    properties = properties,
    outputFiles = outputFiles,
)

fun ProjectNode.toDomainModel(

) = TaskTreeResults.ProjectNode(
    id = id,
    name = name,
)
