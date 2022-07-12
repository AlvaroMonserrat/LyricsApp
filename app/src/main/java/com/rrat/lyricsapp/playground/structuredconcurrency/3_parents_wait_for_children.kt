package com.rrat.lyricsapp.playground.structuredconcurrency

import kotlinx.coroutines.*


fun main()
{
    return runBlocking{

        val scope = CoroutineScope(Dispatchers.Default)

        val parentCoroutineJob = scope.launch {
            launch {
                delay(1000)
                println("Child Coroutine 1 has completed")
            }
            launch {
                delay(1000)
                println("Child Coroutine 2 has completed")
            }
        }
        parentCoroutineJob.join()
        println("Parent has completed")
    }
}