package com.rrat.lyricsapp.playground.cancellation

import kotlinx.coroutines.*
import java.lang.RuntimeException


fun main()
{
    val exceptionHandler = CoroutineExceptionHandler{coroutineContext, throwable ->
        println("Caught $throwable in CoroutineExceptionHandler!")
    }
    val scope = CoroutineScope(Job() + exceptionHandler)

    scope.launch {
        async {
            delay(200)
            throw RuntimeException()
        }
    }

    Thread.sleep(500)
}