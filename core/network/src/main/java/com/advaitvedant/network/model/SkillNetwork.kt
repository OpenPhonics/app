package com.advaitvedant.network.model

data class SkillNetwork(
    override val id: Int,
    val name: String,
    val words: List<SkillWordNetwork> = emptyList(),
    val sentence: List<SkillSentenceNetwork> = emptyList()
) : NetworkModel

data class SkillWordNetwork(
    override val id: Int,
    val word: String,
    val phonic: String?,
    val wordSound: String,
    val translation: String,
    val translationSound: String
) : NetworkModel
data class SkillSentenceNetwork(
    override val id: Int,
    val words: List<SkillWordNetwork>
) : NetworkModel

//data class LanguageNetwork(
//    val id: Int,
//    val name: String,
//    val country: Int,
//    val words: List<WordNetwork>
//)
//data class LevelNetwork(
//    val id: Int,
//    val skill: SkillNetwork,
//    val hardness: Int,
//)
//data class SectionNetwork(
//    val id: Int,
//    val name: Int,
//    val levels: List<LevelNetwork>
//)
//data class CourseNetwork(
//    val id: Int,
//    val targetLanguage: LanguageNetwork,
//    val sourceLanguage: LanguageNetwork,
//    val skills: List<SkillNetwork>
//)
//
//data class WordNetwork(
//    val id: Int,
//    val language: Int,
//    val word: String,
//    val phonic: String?,
//    val wordSound: String
//)