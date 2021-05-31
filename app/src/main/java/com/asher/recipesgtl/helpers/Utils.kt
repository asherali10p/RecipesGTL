package com.asher.recipesgtl.helpers

import java.util.*
import java.util.concurrent.TimeUnit

fun getPastDaysMillis(days: Long) : Long{
        val timeInMillis = TimeUnit.DAYS.toMillis(days)
        return Date().time - timeInMillis
    }