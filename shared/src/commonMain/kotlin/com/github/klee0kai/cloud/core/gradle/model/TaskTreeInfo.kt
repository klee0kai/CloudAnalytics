package com.github.klee0kai.cloud.core.gradle.model

object TaskTreeResults {

    data class ScanResult(
        val projects: List<ProjectNode>,
        val tasks: List<TaskNode>,
    )

    data class ProjectNode(
        val id: Int,
        val name: String,
    )

    data class TaskNode(
        val path: String,
        val name: String,
        val className: String,
        val classLoader: String,
        val projectName: String,
        val group: String,
        val description: String,
        val termDir: String,
        val dependencies: List<String> = mutableListOf(),
        val sourceFiles: List<String> = mutableListOf(),
        val properties: List<String> = mutableListOf(),
        val outputFiles: List<String> = mutableListOf(),
    )

}
