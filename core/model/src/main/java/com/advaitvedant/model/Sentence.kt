package com.advaitvedant.model

data class Sentence(
    override val id: Int,
    val sentence: List<Word>
) : Model