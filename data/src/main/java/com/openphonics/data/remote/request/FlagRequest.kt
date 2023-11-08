package com.openphonics.data.remote.request

data class FlagRequest(
    val flag: String,
    val id: String
)

data class UpdateFlagRequest(
    val flag: String
)
