package com.rrat.lyricsapp.playground.cancellation

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.lang.RuntimeException


fun main()=runBlocking<Unit>{
    try {
        doSomething()
    } catch (e: Exception) {
        println("Caught $e")
    }
}

private suspend fun doSomething()
{
    coroutineScope {
        launch {
            throw RuntimeException()
        }
    }
}