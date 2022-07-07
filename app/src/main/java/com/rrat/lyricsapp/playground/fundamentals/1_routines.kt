package com.rrat.lyricsapp.playground.fundamentals

import info.debatty.java.stringsimilarity.Levenshtein


fun main()
{
    println("main starts")
    routine(1, 500)
    routine(2, 300)
}


fun routine(number: Int, delay: Long)
{
    println("Routine $number starts work")
    val levenshtein: Levenshtein = Levenshtein()
    val distance = levenshtein.distance("My String", "My dasdas")
    println("Distance is : $distance")
    Thread.sleep(delay)
    println("Routine $number has finished")
}