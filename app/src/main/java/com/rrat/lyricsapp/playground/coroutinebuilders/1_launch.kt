package com.rrat.lyricsapp.playground.coroutinebuilders

import kotlinx.coroutines.*


fun main()
{
    runBlocking {
        val job = launch(start = CoroutineStart.LAZY) {
            println(networkRequest())
        }
        println("Out coroutines")
        delay(1000)
        job.start()
    }
    println("main ends  ${Thread.currentThread().name}")
}

suspend fun networkRequest(): String{
    delay(500)
    println("networkRequest  ${Thread.currentThread().name}")
    return "Result"
}