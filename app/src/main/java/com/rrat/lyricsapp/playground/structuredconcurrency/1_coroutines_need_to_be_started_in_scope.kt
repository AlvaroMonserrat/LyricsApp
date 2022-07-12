package com.rrat.lyricsapp.playground.structuredconcurrency

import kotlinx.coroutines.*


val scope = CoroutineScope(Dispatchers.Default)

fun main()= runBlocking {
    val job = scope.launch {
        coroutine(100)
    }
    job.invokeOnCompletion { throwable ->
        if(throwable is CancellationException)
        {
            println("Coroutine was cancelled ${Thread.currentThread().name} ")
        }

    }
    delay(50)
    onDestroy()
}

private suspend fun coroutine(time: Long)
{
    delay(time)
    println("Coroutine completed ${Thread.currentThread().name}")
}

fun onDestroy()
{

    scope.cancel()
    println("life-time of scope ends ${Thread.currentThread().name}")
}