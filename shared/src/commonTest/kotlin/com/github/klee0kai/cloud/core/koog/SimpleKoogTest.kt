package com.github.klee0kai.cloud.core.koog

import ai.koog.agents.core.agent.AIAgent
import ai.koog.prompt.executor.clients.google.GoogleModels
import ai.koog.prompt.executor.clients.openai.OpenAIModels
import ai.koog.prompt.executor.llms.all.simpleGoogleAIExecutor
import ai.koog.prompt.executor.llms.all.simpleOpenAIExecutor
import com.github.klee0kai.cloud.core.utils.runTest
import kotlin.test.Test

/**
 * https://docs.koog.ai/getting-started/
 */
class SimpleKoogTest {

    @Test
    fun simpleTestOpenAI() = runTest {
        // Get an API key from the OPENAI_API_KEY environment variable
        val apiKey =
            ""

        // Create an agent
        val agent = AIAgent(
            promptExecutor = simpleOpenAIExecutor(apiKey),
            llmModel = OpenAIModels.Chat.GPT4_1Mini
        )

        // Run the agent
        val result = agent.run("Hello! How can you help me?")
        println(result)
    }

    @Test
    fun simpleTestGemini() = runTest {
        // Get an API key from the OPENAI_API_KEY environment variable

        // Create an agent
        val agent = AIAgent(
            promptExecutor = simpleGoogleAIExecutor(""),
            llmModel = GoogleModels.Gemini2_0FlashLite001
        )

        // Run the agent
        val result = agent.run("Hello! How can you help me?")
        println(result)
    }


}