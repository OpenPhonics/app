package com.advaitvedant.network.utils

import retrofit2.Response

fun <T> Response<T>.data(): T {
    return if (isSuccessful) {
        body() ?: throw IllegalStateException("Response body is null")
    } else {
        throw Exception("Error!, code {${code()}}")
    }
}
object ResponseCode {
    val NO_AUTH: (Int)->Boolean = {code -> code == 401}
    val BAD_REQUEST: (Int)->Boolean = {code -> code == 400}
    val SUCCESS: (Int)->Boolean = {code -> code == 200}
    val SERVER_ERROR: (Int)->Boolean = {code -> code >= 500}
}
