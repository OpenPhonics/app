package com.advaitvedant.network.model

import kotlinx.serialization.Serializable

@Serializable
data class SentenceNetwork(
    override val id: Int = -1,
    val sentence: List<Int> = emptyList()
) : NetworkModel