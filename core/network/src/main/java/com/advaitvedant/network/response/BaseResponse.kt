package com.advaitvedant.network.response
enum class State {
    SUCCESS, NOT_FOUND, FAILED, UNAUTHORIZED
}
data class BaseResponse<T> (
    val status: State,
    val message: String,
    val data: T
)