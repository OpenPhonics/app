package com.advaitvedant.lesson

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

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
@Composable
fun LessonRoute(
    onBackClick: () -> Unit,
    viewModel: LessonViewModel = hiltViewModel()
){
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center){
        Button(onClick = onBackClick) {
            Text("lesson${viewModel.lessonNum()}")
        }
    }
}
@HiltViewModel
class LessonViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val lessonArgs: LessonArgs = LessonArgs(savedStateHandle)
    fun lessonNum(): Int {
        return lessonArgs.lesson
    }
}