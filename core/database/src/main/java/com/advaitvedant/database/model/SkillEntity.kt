package com.advaitvedant.database.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "skills")
data class SkillBase(
    @PrimaryKey
    override val id: Int,
    val name: String
) : EntityModel


@Entity(tableName = "skill_words")
data class SkillWordEntity(
    @PrimaryKey
    override val id: Int,
    val word: String,
    val phonic: String?,
    @ColumnInfo(name = "word_sound")
    val wordSound: String,
    val translation: String,
    @ColumnInfo(name = "translation_sound")
    val translationSound: String
) : EntityModel

@Entity(tableName = "skill_sentences")
data class SkillSentenceBase(
    @PrimaryKey
    override val id: Int
) : EntityModel
@Entity(primaryKeys = ["skillWordId", "skillSentenceId"])
data class SkillSentenceSkillWordCrossRef(
    val skillWordId: Int,
    @ColumnInfo(index = true)
    val skillSentenceId: Int
)
@Entity(primaryKeys = ["skillWordId", "skillId"])
data class SkillSkillWordCrossRef(
    val skillWordId: Int,
    @ColumnInfo(index = true)
    val skillId: Int
)
@Entity(primaryKeys = ["skillSentenceId", "skillId"])
data class SkillSkillSentenceCrossRef(
    val skillSentenceId: Int,
    @ColumnInfo(index = true)
    val skillId: Int
)

data class SkillSentenceEntity(
    @Embedded val sentence: SkillSentenceBase,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            SkillSentenceSkillWordCrossRef::class,
            parentColumn = "skillSentenceId",
            entityColumn = "skillWordId"
        )
    )
    val words: List<SkillWordEntity>
)
data class SkillEntity(
    @Embedded val skill: SkillBase,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(SkillSkillWordCrossRef::class,
            parentColumn = "skillId",
            entityColumn = "skillWordId"
        )
    )
    val words: List<SkillWordEntity>,
    @Relation(
        entity = SkillSentenceBase::class,
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(SkillSkillSentenceCrossRef::class,
            parentColumn = "skillId",
            entityColumn = "skillSentenceId"
        )
    )
    val sentences: List<SkillSentenceEntity>
) : EntityModel {
    override val id: Int
        get() = skill.id
}
