package com.rrat.lyricsapp.playground.structuredconcurrency

import kotlinx.coroutines.*

fun main() = runBlocking<Unit>{

    val scope = CoroutineScope(Dispatchers.Default)

    scope.coroutineContext.job.invokeOnCompletion {throwable ->
        if(throwable is CancellationException)
        {
            println("Parent job was cancelled")
        }
    }

    scope.launch{
        delay(1000)
        println("Coroutine 1 completed")
    }.invokeOnCompletion { throwable ->
        if(throwable is CancellationException)
        {
            println("Coroutine 1 was cancelled")
        }
    }

    scope.launch {
        delay(1000)
        println("Coroutine 2 completed")
    }.invokeOnCompletion { throwable ->
        if(throwable is CancellationException)
        {
            println("Coroutine 2 was cancelled")
        }
    }

    //scope.cancel()
    scope.coroutineContext.job.cancelAndJoin()
}