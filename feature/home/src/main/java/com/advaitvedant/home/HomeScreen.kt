package com.advaitvedant.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.advaitvedant.design.component.OpTopAppBar
import com.advaitvedant.design.theme.LocalBorderPadding

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeRoute(
    onLessonClick: (Int) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val lessonListUiState by viewModel.lessonListState.collectAsStateWithLifecycle()
    Column(modifier = Modifier.fillMaxSize()) {
        OpTopAppBar(title = stringResource(R.string.openphonics))
        HomeScreen(onLessonClick = onLessonClick, lessonList = lessonListUiState)
    }
}

@Composable
fun HomeScreen(
    onLessonClick: (Int) -> Unit,
    lessonList: LessonListUiState
){
    LazyColumn(modifier = Modifier.padding(LocalBorderPadding.current).fillMaxSize()) {
        when (lessonList) {
            LessonListUiState.Loading -> Unit
            is LessonListUiState.Success -> {
                items(items = lessonList.lessons, key = { it.id }){
                    Button(onClick = {onLessonClick(it.id) }, modifier = Modifier.fillMaxWidth()){
                        Text(it.id.toString())
                        Text(it.num.toString())
                        Text(it.phonetic)
                        Text(it.state.toString())
                    }
                }
            }
        }
    }
}