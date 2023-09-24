package com.openphonics.android.core.model.progress

import com.openphonics.android.core.model.data.Sentence
import com.openphonics.android.core.model.data.Word


data class SectionProgress(
    val currentLesson: Int,
    val isLegendary: Boolean,
    val progressId: String,
    val learnedWords: List<Word>,
    val title: String,
    val order: Int,
    val lessonCount: Int,
    val words: List<Word>,
    val sentences: List<Sentence>,
    val hasData: Boolean,
    val id: String,
)
