package com.rrat.lyricsapp.playground.fundamentals

import android.os.Handler
import android.os.Looper
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.runBlocking


fun main() = runBlocking {
    println("main starts")
    joinAll(
        async { delayDemonstration(1, 500) },
        async { coroutine(2, 300)  }
    )
    println("main ends")
}

suspend fun delayDemonstration(number: Int, delay: Long)
{
    println("Coroutine $number starts work")
    //delay(delay)
    Handler(Looper.getMainLooper())
        .postDelayed({
            println("Courtine $number has finished")
        }, delay)

    println("Coroutine $number has finished")
}
