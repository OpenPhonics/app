package com.advaitvedant.network.model
import com.advaitvedant.model.LessonState
import kotlinx.serialization.Serializable

@Serializable
data class PhoneticLessonNetwork(
    override val id: Int = -1,
    val num: Int = -1,
    val phonetic: String = "",
    val state: LessonState = LessonState.LOCKED,
    val words: List<Int> = emptyList(),
    val sentences: List<Int> = emptyList()
) : NetworkModel

@Serializable
data class PhoneticLessonDynamicNetwork(
    override val id: Int = -1,
    val state: LessonState = LessonState.LOCKED
) : NetworkModel

fun PhoneticLessonNetwork.toDynamic() = PhoneticLessonDynamicNetwork(id, state)