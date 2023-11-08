package com.openphonics.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "flags")
data class FlagEntity(
    @PrimaryKey(autoGenerate=false)
    val id: String,
    val flag: String
)