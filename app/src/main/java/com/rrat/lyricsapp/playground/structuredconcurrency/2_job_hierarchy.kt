package com.rrat.lyricsapp.playground.structuredconcurrency

import kotlinx.coroutines.*

fun main()
{
    val scopeJob = Job()
    val scope = CoroutineScope(Dispatchers.Default + scopeJob)

    val passedJob = Job()
    val coroutineJob = scope.launch{
        println("Starting coroutine")
        delay(50)
    }
    println("Is coroutineJob a child of scopeJob? => ${scopeJob.children.contains(coroutineJob)}")
    coroutineJob.invokeOnCompletion {
        println("Coroutine Completed")
    }

    Thread.sleep(100)

    println("Is coroutineJob a child of scopeJob? => ${scopeJob.children.contains(coroutineJob)}")
    println("passedJob and coroutineJob are references to the same job object: ${passedJob === coroutineJob}")
    //println("Is coroutineJob a child of scopeJob? => ${scopeJob.children.contains(coroutineJob)}")
    println("Is coroutineJob a child of passedJob? => ${passedJob.children.contains(coroutineJob)}")
}