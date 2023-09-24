package com.openphonics.android.data.local.entity.data.base

import com.openphonics.android.core.model.data.Word

data class EntitySentenceBase(
    val language: Int,
    val sentence: List<Word>,
    val sentenceId: Int
)