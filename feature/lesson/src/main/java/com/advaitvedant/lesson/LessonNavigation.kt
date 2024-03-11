package com.advaitvedant.lesson

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

const val LESSON_ROUTE = "lesson_route"
internal const val LESSON_ID_ARG = "lesson_id"

internal class LessonArgs(val lesson: Int) {
    constructor(savedStateHandle: SavedStateHandle) : this(checkNotNull(savedStateHandle.get<Int>(LESSON_ID_ARG)))
}

fun NavController.navigateToLesson(lessonId: Int, navOptions: NavOptions? = null) {
    this.navigate("$LESSON_ROUTE/$lessonId", navOptions)
}

fun NavGraphBuilder.lessonScreen(onBackClick: () -> Unit) {
    composable(
        route = "$LESSON_ROUTE/{$LESSON_ID_ARG}",
        arguments = listOf(
            navArgument(LESSON_ID_ARG) { type = NavType.IntType},
        ),) {
        LessonRoute(onBackClick = onBackClick)
    }
}
