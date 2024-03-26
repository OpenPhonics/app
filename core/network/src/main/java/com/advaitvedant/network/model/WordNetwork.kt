package com.advaitvedant.network.model

import kotlinx.serialization.Serializable

@Serializable
data class WordNetwork(
    override val id: Int,
    val phonetic: String,
    val text: String,
    val translation: String,
    val isLearned: Boolean
) : NetworkModel

@Serializable
data class WordDynamicNetwork(
    override val id: Int,
    val isLearned: Boolean
) : NetworkModel

fun WordNetwork.toDynamic() = WordDynamicNetwork(id, isLearned)