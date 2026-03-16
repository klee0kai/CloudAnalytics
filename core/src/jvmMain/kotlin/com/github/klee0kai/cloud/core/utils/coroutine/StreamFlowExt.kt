package com.github.klee0kai.cloud.core.utils.coroutine

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import java.io.OutputStream


class FlowOutputStream(
    private val channel: Channel<String>
) : OutputStream() {

    private val buffer = StringBuilder()

    override fun write(b: Int) {
        val c = b.toChar()
        if (c == '\n') {
            channel.trySend(buffer.toString() + "\n")
            buffer.clear()
        } else {
            buffer.append(c)
        }
    }

    override fun flush() {
        if (buffer.isNotEmpty()) {
            channel.trySend(buffer.toString())
            buffer.clear()
        }
    }

    override fun close() {
        flush()
        channel.close()
    }

}

object StreamFlow {

    fun createStreamChannel(): Pair<FlowOutputStream, Flow<String>> {
        val outChannel = Channel<String>(Channel.BUFFERED)
        val stdout = FlowOutputStream(outChannel)
        return stdout to outChannel.receiveAsFlow()
    }

}