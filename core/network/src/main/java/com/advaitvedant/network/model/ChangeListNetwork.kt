package com.advaitvedant.network.model


data class ChangeListNetwork(
    override val id: Int = -1,
    val updated: Long = -1,
    val isDelete: Boolean = false
) : NetworkModel