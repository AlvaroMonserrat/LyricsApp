package com.rrat.lyricsapp.playground.cancellation

import kotlinx.coroutines.*
import java.lang.RuntimeException

fun main()= runBlocking<Unit> {

    val exceptionHandler = CoroutineExceptionHandler{
        coroutineContext, throwable ->
        println("ERROR")
    }
    try {
        supervisorScope {
            launch(exceptionHandler) {
                throw RuntimeException()
            }
        }
    }catch (e: Exception)
    {
        println("Caught $e")
    }
}