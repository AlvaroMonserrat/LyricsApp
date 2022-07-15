package com.rrat.lyricsapp.playground.exceptionhandling

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import java.lang.RuntimeException


fun main()
{
    val scope = CoroutineScope(Job())

    scope.launch {
        try{
            launch {
                functionThatThrows()
            }
        }catch (e: Exception)
        {
            println("Exception caught: $e")
        }
    }

    Thread.sleep(100)
}

private fun functionThatThrows()
{
    throw RuntimeException()
}