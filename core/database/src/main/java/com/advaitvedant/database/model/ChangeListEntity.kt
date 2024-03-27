package com.advaitvedant.database.model


interface ChangeListEntity : EntityModel {
    val isDelete: Boolean
    val updated: Long
}

