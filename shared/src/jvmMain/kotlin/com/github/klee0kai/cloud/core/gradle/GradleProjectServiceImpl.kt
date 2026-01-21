package com.github.klee0kai.cloud.core.gradle

import com.github.klee0kai.cloud.core.di.identifier.GradleProjectId
import com.github.klee0kai.cloud.core.gradle.model.ProjectInfo
import com.github.klee0kai.cloud.core.gradle.model.toProjectInfo
import org.gradle.tooling.GradleConnector
import org.gradle.tooling.model.GradleProject
import java.io.File

class GradleProjectServiceImpl(
    val gradleProjectId: GradleProjectId,
) : GradleProjectService {

    private val connection
        get() = GradleConnector.newConnector()
            .forProjectDirectory(File(gradleProjectId.projectPath))
            .connect()


    override suspend fun info(): ProjectInfo {
        connection.use { connection ->
            val rootProject = connection.getModel(GradleProject::class.java)
            return rootProject.toProjectInfo()
        }

    }


}


