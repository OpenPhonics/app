package com.openphonics.android.core.model.data

import com.openphonics.android.data.local.entity.data.base.EntitySentenceBase

data class Sentence(
    val language: Int,
    val sentence: List<Word>,
    val id: Int
){
    companion object {
        fun fromEntityBase(entity: EntitySentenceBase): Sentence = Sentence(entity.language, entity.sentence, entity.sentenceId)
    }
}