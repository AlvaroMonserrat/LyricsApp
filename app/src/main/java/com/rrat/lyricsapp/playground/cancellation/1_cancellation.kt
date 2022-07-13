package com.rrat.lyricsapp.playground.cancellation

import kotlinx.coroutines.*

fun main() = runBlocking {

    val job = launch() {
        repeat(10)
        {
            index->
            println("operation number $index")

            try {
                delay(100)
            }catch (exception: CancellationException)
            {
                println("CancellationException was thrown!")
                throw CancellationException()
            }

        }
    }
    delay(250)
    println("Cancelling coroutine")
    job.cancel()
}