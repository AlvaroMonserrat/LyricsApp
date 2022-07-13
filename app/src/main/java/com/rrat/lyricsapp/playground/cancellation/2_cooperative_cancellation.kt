package com.rrat.lyricsapp.playground.cancellation

import kotlinx.coroutines.*

fun main()= runBlocking{

    val job = launch(Dispatchers.Default) {
        repeat(10)
        {
            index ->
            if(isActive)
            {
                println("operation number $index")
                Thread.sleep(1000)
            }else{
                withContext(NonCancellable)
                {
                    delay(100)
                    println("Cleaning up... ")
                    throw CancellationException()
                }

            }

        }
    }
    delay(250)
    println("Cancelling Coroutine")
    job.invokeOnCompletion { it->
        if (it != null) {
            println("Exception " + it.message)
        }
    }
    job.cancel()

}