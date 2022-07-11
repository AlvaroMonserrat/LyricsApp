package com.rrat.lyricsapp.playground.coroutinebuilders

import kotlinx.coroutines.*

fun main() = runBlocking {

    println("run block main start ${Thread.currentThread().name}")

    val startTime = System.currentTimeMillis()

    val deferred1 = async(start = CoroutineStart.LAZY) {
        val result1 = networkCallAsync(1)
        println("result received: $result1 after ${elapsedMillisAsync(startTime)}ms ${Thread.currentThread().name}")
        result1
    }
    val deferred2 = async {
        val result2 = networkCallAsync(2)
        println("result received: $result2 after ${elapsedMillisAsync(startTime)}ms ${Thread.currentThread().name}")
        result2
    }
    deferred1.start()
    
    val resultList = listOf(deferred1.await(), deferred2.await())
    println("run block main finish List: $resultList after ${elapsedMillisAsync(startTime)}ms ${Thread.currentThread().name}")
}

suspend fun networkCallAsync(number: Int): String
{
    delay(500)
    return "Result $number"
}

fun elapsedMillisAsync(starTime: Long) = System.currentTimeMillis() - starTime