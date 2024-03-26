package com.advaitvedant.model

data class Word(
    override val id: Int,
    val phonetic: String,
    val text: String,
    val translation: String,
    val isLearned: Boolean
) : Model