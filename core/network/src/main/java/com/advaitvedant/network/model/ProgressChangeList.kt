package com.advaitvedant.network.model


data class ProgressChangeList(
    override val id: Int,
    val updated: Long,
    val isDelete: Boolean
) : NetworkModel