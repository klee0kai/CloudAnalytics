package com.github.klee0kai.cloud.lifecycle

import java.io.File


class InitHelper {

    fun initStaticResourcesFolder() {
        copyFromResources("")
    }

    fun copyFromResources(fileName: String) {
        File("static").mkdirs()
        val input = this::class.java.getResourceAsStream(fileName)
            .use { input ->
                File("static", fileName).outputStream().use { out ->
                    input?.transferTo(out)
                }
            }
    }

}