package com.openphonics.android.core.model.progress

data class UnitProgress(
    val title: String,
    val order: Int,
    val sections: List<SectionProgress>,
    val hasData: Boolean,
    val progressId: String,
    val id: String
)