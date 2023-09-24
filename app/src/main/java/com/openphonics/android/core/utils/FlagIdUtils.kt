package com.openphonics.android.core.utils

fun encodeFlag(str: String): Int {
    val base = 26
    val strLength = str.length
    var uniqueInt = 0
    var positionValue = 1

    for (i in strLength - 1 downTo 0) {
        val charValue = str[i] - 'a' + 1
        uniqueInt += charValue * positionValue
        positionValue *= base
    }
    return uniqueInt
}

fun decodeFlag(uniqueInt: Int): String {
    val base = 26
    var remainingValue = uniqueInt
    val result = StringBuilder()

    while (remainingValue > 0) {
        val charValue = (remainingValue - 1) % base
        val char = (charValue + 'a'.toInt()).toChar()
        result.append(char)
        remainingValue = (remainingValue - charValue) / base
    }

    return result.reverse().toString()
}