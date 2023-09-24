package com.openphonics.android.data.remote.model.response

data class LanguageProgress(
    val progressId: String = "",
    val started: Long = 0,
    val updated: Long = 0,
    val streak: Int = 0,
    val xp: Int = 0,
    val nativeId: String = "",
    val languageId: String = "",
    val languageName: String = "",
    val flag: String = "",
    val units: List<UnitProgress> = emptyList(),
    val hasData: Boolean = false

)
data class UnitProgress(
    val title: String,
    val order: Int,
    val sections: List<SectionProgress>,
    val hasData: Boolean,
    val progressId: String
)
data class SectionProgress(
    val currentLesson: Int,
    val isLegendary: Boolean,
    val progressId: String,
    val learnedWords: List<Word>,
    val title: String,
    val order: Int,
    val lessonCount: Int,
    val words: List<Word>,
    val sentences: List<Sentence>,
    val hasData: Boolean,
)
data class LanguageProgressResponse(
    override val status: State,
    override val message: String,
    val progress: List<LanguageProgress> = emptyList()
)  : Response {
    companion object {
        fun unauthorized(message: String) = LanguageProgressResponse(
            State.UNAUTHORIZED,
            message
        )

        fun success(languageProgress: List<LanguageProgress>) = LanguageProgressResponse(
            State.SUCCESS,
            "Task successful",
            languageProgress
        )
        fun success(languageProgress: LanguageProgress) = LanguageProgressResponse(
            State.SUCCESS,
            "Task successful",
            listOf(languageProgress)
        )

        fun failed(message: String) = LanguageProgressResponse(
            State.FAILED,
            message
        )

        fun notFound(message: String) = LanguageProgressResponse(
            State.NOT_FOUND,
            message
        )
    }
}

data class UnitProgressResponse(
    override val status: State,
    override val message: String,
    val progress: List<UnitProgress> = emptyList()
)  : Response {
    companion object {
        fun unauthorized(message: String) = UnitProgressResponse(
            State.UNAUTHORIZED,
            message
        )

        fun success(unitProgress: List<UnitProgress>) = UnitProgressResponse(
            State.SUCCESS,
            "Task successful",
            unitProgress
        )
        fun success(unitProgress: UnitProgress) = UnitProgressResponse(
            State.SUCCESS,
            "Task successful",
            listOf(unitProgress)
        )

        fun failed(message: String) = UnitProgressResponse(
            State.FAILED,
            message
        )

        fun notFound(message: String) = UnitProgressResponse(
            State.NOT_FOUND,
            message
        )
    }
}



data class SectionProgressResponse(
    override val status: State,
    override val message: String,
    val progress: List<SectionProgress> = emptyList()
)  : Response {
    companion object {
        fun unauthorized(message: String) = UnitProgressResponse(
            State.UNAUTHORIZED,
            message
        )

        fun success(unitProgress: List<UnitProgress>) = UnitProgressResponse(
            State.SUCCESS,
            "Task successful",
            unitProgress
        )
        fun success(unitProgress: UnitProgress) = UnitProgressResponse(
            State.SUCCESS,
            "Task successful",
            listOf(unitProgress)
        )

        fun failed(message: String) = UnitProgressResponse(
            State.FAILED,
            message
        )

        fun notFound(message: String) = UnitProgressResponse(
            State.NOT_FOUND,
            message
        )
    }
}



