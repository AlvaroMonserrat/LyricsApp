package com.rrat.lyricsapp.playground.structuredconcurrency

import kotlinx.coroutines.*


fun main()
{
    val scope = CoroutineScope(Job())

    scope.launch{

        doSomeTasks()

        val job3 = launch {
            println("Starting task 3")
            delay(300)
            println("Task 3 completed")
        }
    }
    Thread.sleep(1000)
}

suspend fun doSomeTasks() = coroutineScope{
    val job1 = launch {
        println("Starting task 1")
        delay(100)
        println("Task 1 completed")
    }
    val job2 = launch {
        println("Starting task 2")
        delay(200)
        println("Task 2 completed")
    }
}