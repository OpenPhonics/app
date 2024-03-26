package com.advaitvedant.network.model


data class NetworkChangeList(
    override val id: Int,
    val updated: Long,
    val isDelete: Boolean
) : NetworkModel