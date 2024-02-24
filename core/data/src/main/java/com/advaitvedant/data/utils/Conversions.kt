package com.advaitvedant.data.utils

import com.advaitvedant.common.model.Sentence
import com.advaitvedant.common.model.Skill
import com.advaitvedant.common.model.SkillWithData
import com.advaitvedant.common.model.Word
import com.advaitvedant.database.model.SkillBase
import com.advaitvedant.database.model.SkillEntity
import com.advaitvedant.database.model.SkillSentenceBase
import com.advaitvedant.database.model.SkillSentenceEntity
import com.advaitvedant.database.model.SkillWordEntity
import com.advaitvedant.network.model.SkillNetwork
import com.advaitvedant.network.model.SkillSentenceNetwork
import com.advaitvedant.network.model.SkillWordNetwork

fun SkillNetwork.asEntity(): SkillEntity = SkillEntity(
    SkillBase(
        id = this.id,
        name = this.name
    ),
    words = this.words.map(SkillWordNetwork::asEntity),
    sentences = this.sentence.map(SkillSentenceNetwork::asEntity)
)
fun SkillEntity.asSkill(): SkillWithData = SkillWithData(
    id = this.skill.id,
    name = this.skill.name,
    words = this.words.map(SkillWordEntity::asWord),
    sentences = this.sentences.map(SkillSentenceEntity::asSentence)
)
fun SkillBase.asSkill(): Skill = Skill(
    id = this.id,
    name = this.name
)
fun SkillWordNetwork.asEntity(): SkillWordEntity = SkillWordEntity(
    id = this.id,
    word = this.word,
    phonic = this.phonic,
    wordSound = this.wordSound,
    translation = this.translation,
    translationSound = this.translationSound
)
fun SkillSentenceNetwork.asEntity(): SkillSentenceEntity = SkillSentenceEntity(
    sentence = SkillSentenceBase(
        id = this.id
    ),
    words = this.words.map(SkillWordNetwork::asEntity)
)
fun SkillWordEntity.asWord(): Word = Word(
    id = this.id,
    word = this.word,
    phonic = this.phonic,
    translationSound = this.translationSound,
    translation = this.translation,
    wordSound = this.wordSound
)

fun SkillSentenceEntity.asSentence(): Sentence = Sentence(
    id = this.sentence.id,
    words = this.words.map(SkillWordEntity::asWord)
)