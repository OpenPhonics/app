package com.advaitvedant.lesson

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun LessonRoute(
    viewModel: LessonViewModel = hiltViewModel(),
    onBackClick: () -> Unit){
    Column(modifier = Modifier.fillMaxSize()){
        val loading = viewModel.loading.collectAsStateWithLifecycle()
        if (loading.value) {
            Button(onClick = viewModel::playSound) {
                Text("CLICK ME")
            }
        } else {
            Text("LODAING")
        }
    }
}