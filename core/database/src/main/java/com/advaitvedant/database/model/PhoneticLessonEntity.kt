package com.advaitvedant.database.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.PrimaryKey
import androidx.room.Relation
import androidx.room.TypeConverter
import com.advaitvedant.model.LessonState

@Entity("phonetic_lesson_change_list_entity")
data class PhoneticLessonChangeListEntity(
    @PrimaryKey
    override val id: Int = 1,
    @ColumnInfo(name = "is_delete")
    override val isDelete: Boolean = false,
    override val updated: Long = -1
) : ChangeListEntity


@Entity("phonetic_lesson")
data class PhoneticLessonEntity(
    @PrimaryKey
    override val id: Int = -1,
    val num: Int = -1,
    val phonetic: String = "",
    val state: LessonState = LessonState.LOCKED,
) : EntityModel

data class PopulatedPhoneticLesson(
    @Embedded
    val entity: PhoneticLessonEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = PhoneticLessonWordCrossRef::class,
            parentColumn = "phonetic_lesson_id",
            entityColumn = "word_id"
        )
    )
    val words: List<WordEntity>,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = PhoneticLessonSentenceCrossRef::class,
            parentColumn = "phonetic_lesson_id",
            entityColumn = "sentence_id"
        )
    )
    val sentences: List<SentenceEntity>,
)
class LessonStateConverter {
    @TypeConverter
    fun fromLessonState(value: LessonState): Int {
        return value.ordinal
    }

    @TypeConverter
    fun toLessonState(value: Int): LessonState {
        return LessonState.entries[value]
    }
}
