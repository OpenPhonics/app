package com.advaitvedant.common.model

data class Skill(
    override val id: Int,
    val name: String
) : BaseModel

data class SkillWithData(
    override val id: Int,
    val name: String,
    val words: List<Word>,
    val sentences: List<Sentence>
) : BaseModel

data class Word(
    override val id: Int,
    val word: String,
    val phonic: String?,
    val wordSound: String,
    val translation: String,
    val translationSound: String
) : BaseModel

data class Sentence(
    override val id: Int,
    val words: List<Word>
): BaseModel