package com.openphonics.android.data.local.entity.data.base

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "units")
data class EntityUnitBase(
    val language: Int,
    val title: String,
    val order: Int,
    @PrimaryKey(autoGenerate = false)
    val unitId: Int
)