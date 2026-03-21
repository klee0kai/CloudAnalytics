package com.github.klee0kai.cloud.core.kotlin.psi

import org.jetbrains.kotlin.com.intellij.openapi.vfs.VirtualFile
import org.jetbrains.kotlin.com.intellij.openapi.vfs.VirtualFileSystem
import java.io.InputStream
import java.io.OutputStream

class WritableVirtualFileWrapper(
    private val original: VirtualFile
) : VirtualFile() {

    override fun isWritable(): Boolean = true

    override fun getName(): String = original.name
    override fun getFileSystem(): VirtualFileSystem = original.fileSystem
    override fun getPath(): String = original.path
    override fun isDirectory(): Boolean = original.isDirectory
    override fun isValid(): Boolean = original.isValid
    override fun getParent(): VirtualFile? = original.parent
    override fun getChildren(): Array<VirtualFile> = original.children

    override fun getOutputStream(requestor: Any?, newModificationStamp: Long, newTimeStamp: Long): OutputStream {
        return original.getOutputStream(requestor, newModificationStamp, newTimeStamp)
    }

    override fun contentsToByteArray(): ByteArray = original.contentsToByteArray()
    override fun getTimeStamp(): Long = original.timeStamp
    override fun getLength(): Long = original.length
    override fun refresh(asynchronous: Boolean, recursive: Boolean, postRunnable: Runnable?) {
        original.refresh(asynchronous, recursive, postRunnable)
    }

    override fun getInputStream(): InputStream = original.inputStream

    fun getOriginal() = original
}