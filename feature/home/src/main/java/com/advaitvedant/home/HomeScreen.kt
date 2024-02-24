package com.advaitvedant.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridItemScope
import androidx.compose.foundation.lazy.grid.LazyGridItemSpanScope
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.advaitvedant.design.component.OpOverlayLoadingWheel
import com.advaitvedant.design.supportWideScreen

@Composable
fun HomeRoute(
    viewModel: HomeViewModel = hiltViewModel(),
    onNavigateToLogin: () -> Unit,
    onNavigateToSkill: (Int) -> Unit
){
    val skillState by viewModel.skillState.collectAsStateWithLifecycle()
    val isSyncing by viewModel.isSyncing.collectAsStateWithLifecycle()
    val isLoggedIn by viewModel.isLoggedIn.collectAsStateWithLifecycle(initialValue = null)
    HomeScreen(
        skillState = skillState,
        isSyncing = isSyncing,
        onNavigateToSkill = onNavigateToSkill,
        logout = viewModel::logout
    )
    LaunchedEffect(isLoggedIn ){
        if (isLoggedIn != null && !isLoggedIn!!){
            onNavigateToLogin()
        }
    }
}

@Composable
internal fun HomeScreen(
    modifier: Modifier = Modifier,
    skillState: SkillUiState,
    isSyncing: Boolean,
    onNavigateToSkill: (Int) -> Unit,
    logout: ()->Unit
    ){
    val isSkillLoading = skillState is SkillUiState.Loading
    val state = rememberLazyGridState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .supportWideScreen(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HomeTopBar(logout)
        LazyVerticalGrid(
            columns = GridCells.Adaptive(64.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp),
            state = state
        ) {
            skillGrid(
                skillState,
                onNavigateToSkill
            )
        }
        AnimatedVisibility(
            visible = isSyncing || isSkillLoading,
            enter = slideInVertically(
                initialOffsetY = { fullHeight -> -fullHeight },
            ) + fadeIn(),
            exit = slideOutVertically(
                targetOffsetY = { fullHeight -> -fullHeight },
            ) + fadeOut(),
        ) {
            val loadingContentDescription = "loading"
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
            ) {
                OpOverlayLoadingWheel(
                    modifier = Modifier
                        .align(Alignment.Center),
                    contentDesc = loadingContentDescription,
                )
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(
    logout: ()->Unit
){
    Card(
        onClick = logout,
        modifier = Modifier
            .padding(top = 16.dp)
            .fillMaxWidth()
            .height(64.dp)
            .clip(RoundedCornerShape(16.dp)),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        )
    ){
        Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            text = "LOGOUT",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
    }
}
fun LazyGridScope.skillGrid(
    skillState: SkillUiState,
    onSkillClick: (Int) -> Unit,
) {
    when (skillState) {
        SkillUiState.Loading -> Unit
        is SkillUiState.Success -> {
            items(items = skillState.skills, key = { it.id }){skill->
                CharacterButton(
                    name = skill.name,
                    onClick = { onSkillClick(skill.id)}
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterButton(
    modifier: Modifier = Modifier,
    name: String,
    onClick: () -> Unit
){
    Card(
        onClick = { onClick() },
        modifier = modifier
            .fillMaxSize()
            .aspectRatio(1f)
            .background(
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = RoundedCornerShape(16.dp)
            )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = name,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

//@Preview
//@Composable
//fun SkillCardPreview(){
//    val skills = listOf(
//        SkillModel(
//                id = 0,
//                name = "i",
//                words = emptyList(),
//                sentences = emptyList()
//            ),
//        SkillModel(
//            id = 1,
//            name = "i",
//            words = emptyList(),
//            sentences = emptyList()
//        ),
//        SkillModel(
//            id = 2,
//            name = "i",
//            words = emptyList(),
//            sentences = emptyList()
//        ),
//        SkillModel(
//            id = 3,
//            name = "i",
//            words = emptyList(),
//            sentences = emptyList()
//        ),
//        SkillModel(
//            id = 4,
//            name = "i",
//            words = emptyList(),
//            sentences = emptyList()
//        ),
//        SkillModel(
//            id = 5,
//            name = "i",
//            words = emptyList(),
//            sentences = emptyList()
//        ),
//        SkillModel(
//            id = 6,
//            name = "i",
//            words = emptyList(),
//            sentences = emptyList()
//        ),
//        SkillModel(
//            id = 7,
//            name = "i",
//            words = emptyList(),
//            sentences = emptyList()
//        )
//        )
//    OpTheme {
//        OpBackground {
//            val state = rememberLazyGridState()
//            Box(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(8.dp),
//            ) {
//                LazyVerticalGrid(
//                    columns = GridCells.Adaptive(64.dp),
//                    verticalArrangement = Arrangement.spacedBy(16.dp),
//                    horizontalArrangement = Arrangement.spacedBy(16.dp),
//                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp),
//                    state = state
//                ) {
//                    items(items = skills, key = { it.id }){
//                        CharacterButton(
//                            skill = SkillModel(
//                                id = it.id,
//                                name = it.name,
//                                words = emptyList(),
//                                sentences = emptyList()
//                            ),
//                            onClick = { }
//                        )
//                    }
//                }
//            }
//        }
//    }
//}
