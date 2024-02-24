package com.advaitvedant.network.model


data class StaticChangeList(
    override val id: Int,
    val version: Int,
    val isDelete: Boolean
) : NetworkModel