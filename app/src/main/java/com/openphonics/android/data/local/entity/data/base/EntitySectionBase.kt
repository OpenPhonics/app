package com.openphonics.android.data.local.entity.data.base

import com.openphonics.android.core.model.data.Sentence
import com.openphonics.android.core.model.data.Word

data class EntitySectionBase (
    val title: String,
    val order: Int,
    val lessonCount: Int,
    val unit: Int,
    val sectionId: Int
)
