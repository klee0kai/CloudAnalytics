package com.github.klee0kai.cloud.core.kotlin.mock

import org.jetbrains.kotlin.com.intellij.mock.MockProject
import org.jetbrains.kotlin.com.intellij.openapi.util.Key
import org.jetbrains.kotlin.com.intellij.openapi.util.UserDataHolderBase
import org.jetbrains.kotlin.com.intellij.pom.PomModel
import org.jetbrains.kotlin.com.intellij.pom.PomModelAspect
import org.jetbrains.kotlin.com.intellij.pom.PomTransaction
import org.jetbrains.kotlin.com.intellij.pom.tree.TreeAspect

class PomModelMock(
    val project: MockProject,
) : PomModel {
    private val userDataHolder = UserDataHolderBase()
    override fun <T : PomModelAspect> getModelAspect(aspectClass: Class<T>): T? {
        if (aspectClass == TreeAspect::class.java) {
            @Suppress("UNCHECKED_CAST")
            return project.getComponent(TreeAspect::class.java) as T
        }
        return null
    }

    override fun runTransaction(transaction: PomTransaction) {
        transaction.run()
    }

    override fun <T : Any?> getUserData(key: Key<T>): T? {
        return userDataHolder.getUserData(key)
    }

    override fun <T : Any?> putUserData(key: Key<T>, value: T?) {
        userDataHolder.putUserData(key, value)
    }
}