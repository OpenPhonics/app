package com.advaitvedant.network.response
enum class State {
    SUCCESS, NOT_FOUND, FAILED, UNAUTHORIZED
}
data class BaseResponse<T> (
    val data: T
)