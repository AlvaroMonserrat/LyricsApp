package com.rrat.lyricsapp.playground.coroutinebuilders

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {

    println("run block main start ${Thread.currentThread().name}")

    val startTime = System.currentTimeMillis()

    val resultList = mutableListOf<String>()

    val job1 = launch {
        val result1 = networkCall(1)
        resultList.add(result1)
        println("result received: $result1 after ${elapsedMillis(startTime)}ms ${Thread.currentThread().name}")
    }
    val job2 = launch {
        val result2 = networkCall(2)
        resultList.add(result2)
        println("result received: $result2 after ${elapsedMillis(startTime)}ms ${Thread.currentThread().name}")
    }
    job1.join()
    job2.join()
    println("run block main finish List: $resultList after ${elapsedMillis(startTime)}ms ${Thread.currentThread().name}")
}

suspend fun networkCall(number: Int): String
{
    delay(500)
    return "Result $number"
}

fun elapsedMillis(starTime: Long) = System.currentTimeMillis() - starTime