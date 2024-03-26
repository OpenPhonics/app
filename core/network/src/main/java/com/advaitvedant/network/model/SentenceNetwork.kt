package com.advaitvedant.network.model

import kotlinx.serialization.Serializable

@Serializable
data class SentenceNetwork(
    override val id: Int,
    val sentence: List<Int>
) : NetworkModel