package com.advaitvedant.network.model

import kotlinx.serialization.Serializable

@Serializable
data class WordNetwork(
    override val id: Int = -1,
    val phonetic: String = "",
    val text: String = "",
    val translation: String = "",
    val isLearned: Boolean = false
) : NetworkModel

@Serializable
data class WordDynamicNetwork(
    override val id: Int = -1,
    val isLearned: Boolean = false
) : NetworkModel

fun WordNetwork.toDynamic() = WordDynamicNetwork(id, isLearned)