package com.github.klee0kai.cloud.core.gradle

import com.github.klee0kai.cloud.core.di.identifier.GradleProjectId
import com.github.klee0kai.cloud.core.gradle.model.ProjectInfo
import com.github.klee0kai.cloud.core.gradle.model.TaskTreeResults
import com.github.klee0kai.cloud.core.gradle.model.toDomainModel
import com.github.klee0kai.cloud.core.gradle.model.toProjectInfo
import com.github.klee0kai.cloud.core.utils.coroutine.StreamFlow
import com.github.klee0kai.cloud.core.utils.coroutine.childSupervisedScope
import com.github.klee0kai.cloud.core.utils.poet.codeFlow
import com.github.klee0kai.cloud.core.utils.poet.genKotlinScript
import com.github.klee0kai.tasktree.output.TaskTreeResult
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.firstOrNull
import org.gradle.tooling.GradleConnector
import org.gradle.tooling.ProgressListener
import org.gradle.tooling.model.GradleProject
import org.gradle.tooling.model.build.BuildEnvironment
import org.gradle.tooling.model.idea.IdeaProject
import java.io.File

class GradleProjectServiceImpl(
    val gradleProjectId: GradleProjectId,
) : GradleProjectService {

    private val defaultScope = CoroutineScope(Dispatchers.Default)
    private val ioScope = CoroutineScope(Dispatchers.IO)

    private val connection
        get() = GradleConnector.newConnector()
            .forProjectDirectory(File(gradleProjectId.projectPath))
            .useGradleUserHomeDir(File(".gradle-tapi-1").absoluteFile)
            .connect()


    override suspend fun info(): Result<ProjectInfo> = connection.use { connection ->
        runCatching {
            val rootProject = connection.getModel(GradleProject::class.java)
            val ideProject = connection.getModel(IdeaProject::class.java)
            val buildEnv = connection.getModel(BuildEnvironment::class.java)

            rootProject.toProjectInfo()
        }
    }


    override suspend fun taskTree(): Result<TaskTreeResults.ScanResult> = connection.use { connection ->
        return@use coroutineScope {
            val childrenScope = childSupervisedScope()
            val initGradleFile = "/home/panda/Documents/IdeaProjects/CloudAnalytics/shared/build/tmp/init.gradle.kts"
            genKotlinScript(fileName = "init.gradle") {
                codeFlow("initscript") {
                    codeFlow("repositories") {
                        addStatement("mavenCentral()")
                        addStatement("gradlePluginPortal()")
                        addStatement("maven(url = %S)", "https://jitpack.io")
                        addStatement("mavenLocal()")
                    }
                    codeFlow("dependencies") {
                        addStatement("classpath(%S)", "com.github.klee0kai.tasktree:tasktree:0.0.12_socket")
                    }
                }
                codeFlow("rootProject") {
                    addStatement("pluginManager.apply(com.github.klee0kai.tasktree.TaskTreePlugin::class.java)")
                }
            }.writeTo(File(initGradleFile).parentFile)

            val (port, receiveFlow) = childrenScope.receiveModel<TaskTreeResult>()
            val (stdout, stdOutFlow) = StreamFlow.createStreamChannel()
            val (stderr, stdErrFlow) = StreamFlow.createStreamChannel()
            childrenScope.launch(Dispatchers.IO) { stdOutFlow.collect { print(it) } }
            childrenScope.launch(Dispatchers.IO) { stdErrFlow.collect { print(it) } }

            val buildResult = connection.newBuild()
                .setStandardOutput(stdout)
                .setStandardError(stderr)
                .addProgressListener(ProgressListener { event ->
                    println("${event.description}")
                })
                .withArguments("--init-script", initGradleFile, "--stacktrace")
                .forTasks("taskTree", "--outputJson", "http://localhost:${port}/")
                .runAsync()

            childrenScope.cancel()

            val dataResult = receiveFlow.firstOrNull()
            return@coroutineScope when {
                buildResult.isFailure -> Result.failure(buildResult.exceptionOrNull() ?: IllegalStateException("build"))
                dataResult == null -> Result.failure(IllegalStateException("No result"))
                else -> Result.success(dataResult.toDomainModel())
            }
        }
    }

}



