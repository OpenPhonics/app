package com.openphonics.android.data.remote.model.response

import com.openphonics.android.core.model.data.*
import com.openphonics.android.core.model.data.Unit

data class FlagResponse(
    override val status: State,
    override val message: String,
    val flag: List<Flag> = emptyList()
)  : Response {
    companion object {
        fun unauthorized(message: String) = FlagResponse(
            State.UNAUTHORIZED,
            message
        )

        fun success(flag: List<Flag>) = FlagResponse(
            State.SUCCESS,
            "Task successful",
            flag
        )
        fun success(flag: Flag) = FlagResponse(
            State.SUCCESS,
            "Task successful",
            listOf(flag)
        )

        fun failed(message: String) = FlagResponse(
            State.FAILED,
            message
        )

        fun notFound(message: String) = FlagResponse(
            State.NOT_FOUND,
            message
        )
    }
}


data class LanguageResponse(
    override val status: State,
    override val message: String,
    val language: List<Language> = emptyList()
)  : Response {
    companion object {
        fun unauthorized(message: String) = LanguageResponse(
            State.UNAUTHORIZED,
            message
        )

        fun success(language: List<Language>) = LanguageResponse(
            State.SUCCESS,
            "Task successful",
            language
        )
        fun success(language: Language) = LanguageResponse(
            State.SUCCESS,
            "Task successful",
            listOf(language)
        )

        fun failed(message: String) = LanguageResponse(
            State.FAILED,
            message
        )

        fun notFound(message: String) = LanguageResponse(
            State.NOT_FOUND,
            message
        )
    }
}

data class UnitResponse(
    override val status: State,
    override val message: String,
    val unit: List<Unit> = emptyList()
)  : Response {
    companion object {
        fun unauthorized(message: String) = UnitResponse(
            State.UNAUTHORIZED,
            message
        )

        fun success(unit: List<Unit>) = UnitResponse(
            State.SUCCESS,
            "Task successful",
            unit
        )
        fun success(unit: Unit) = UnitResponse(
            State.SUCCESS,
            "Task successful",
            listOf(unit)
        )

        fun failed(message: String) = UnitResponse(
            State.FAILED,
            message
        )

        fun notFound(message: String) = UnitResponse(
            State.NOT_FOUND,
            message
        )
    }
}


data class SectionResponse(
    override val status: State,
    override val message: String,
    val section: List<Section> = emptyList()
)  : Response {
    companion object {
        fun unauthorized(message: String) = SectionResponse(
            State.UNAUTHORIZED,
            message
        )

        fun success(section: List<Section>) = SectionResponse(
            State.SUCCESS,
            "Task successful",
            section
        )
        fun success(section: Section) = SectionResponse(
            State.SUCCESS,
            "Task successful",
            listOf(section)
        )

        fun failed(message: String) = SectionResponse(
            State.FAILED,
            message
        )

        fun notFound(message: String) = SectionResponse(
            State.NOT_FOUND,
            message
        )
    }
}


data class WordResponse(
    override val status: State,
    override val message: String,
    val word: List<Word> = emptyList()
)  : Response {
    companion object {
        fun unauthorized(message: String) = WordResponse(
            State.UNAUTHORIZED,
            message
        )

        fun success(word: List<Word>) = WordResponse(
            State.SUCCESS,
            "Task successful",
            word
        )
        fun success(word: Word) = WordResponse(
            State.SUCCESS,
            "Task successful",
            listOf(word)
        )

        fun failed(message: String) = WordResponse(
            State.FAILED,
            message
        )

        fun notFound(message: String) = WordResponse(
            State.NOT_FOUND,
            message
        )
    }
}


data class SentenceResponse(
    override val status: State,
    override val message: String,
    val sentence: List<Sentence> = emptyList()
)  : Response {
    companion object {
        fun unauthorized(message: String) = SentenceResponse(
            State.UNAUTHORIZED,
            message
        )

        fun success(sentence: List<Sentence>) = SentenceResponse(
            State.SUCCESS,
            "Task successful",
            sentence
        )
        fun success(sentence: Sentence) = SentenceResponse(
            State.SUCCESS,
            "Task successful",
            listOf(sentence)
        )

        fun failed(message: String) = SentenceResponse(
            State.FAILED,
            message
        )

        fun notFound(message: String) = SentenceResponse(
            State.NOT_FOUND,
            message
        )
    }
}





