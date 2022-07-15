package com.rrat.lyricsapp.playground.lambdaexpression



fun main()
{
    val data = lambdaFunction {
        println("Lambda Expression $it")
    }
    println("data received $data")
}


fun lambdaFunction(block: (data: Int)->Unit): Int {
    val value = 10
    block.invoke(value)
    return value
}