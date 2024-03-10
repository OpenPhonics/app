package com.advaitvedant.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.advaitvedant.design.component.OpTopAppBar
import com.advaitvedant.design.theme.LocalBorderPadding
import kotlinx.coroutines.flow.Flow

@Composable
fun SettingsRoute(
    viewModel: SettingsViewModel = hiltViewModel(),
    onSignOut: () -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()){
        OpTopAppBar(title = stringResource(R.string.settings))
        SettingsScreen(
            onSignOut = {
                viewModel.logout()
                onSignOut()
            },
            isDarkMode = viewModel.darkTheme,
            switchTheme = viewModel::switchDarkTheme
        )
    }

}

@Composable
fun SettingsScreen(
    onSignOut: () -> Unit,
    isDarkMode: Flow<Boolean>,
    switchTheme: (Boolean) -> Unit
){
    val darkMode = isDarkMode.collectAsStateWithLifecycle(initialValue = false)
    Column(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier
                .padding(
                    top = LocalBorderPadding.current.calculateTopPadding()
                )
                .fillMaxWidth()
        ) {
            SettingsBlockSwitch(
                text = stringResource(R.string.dark_mode),
                checked = darkMode.value,
                onSwitch = switchTheme
            )
            SettingsBlockButton(
                textColor = MaterialTheme.colorScheme.error,
                text = stringResource(R.string.sign_out),
                onClick = onSignOut
            )
        }
    }
}




@Composable
fun SettingsBlockButton(textColor: Color, text: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surfaceBright)
            .horizontalBorder(1.dp, MaterialTheme.colorScheme.onSurfaceVariant)
    ) {
        TextButton(onClick = onClick) {
            Text(text = text, color = textColor,
                fontSize = MaterialTheme.typography.titleMedium.fontSize
            )
        }
    }
}
@Composable
fun SettingsBlockSwitch(text: String, checked: Boolean, onSwitch: (Boolean) -> Unit){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surfaceBright)
            .horizontalBorder(1.dp, MaterialTheme.colorScheme.onSurfaceVariant)
            .padding(horizontal = LocalBorderPadding.current.calculateTopPadding()),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = MaterialTheme.typography.titleMedium.fontSize
        )
        Switch(
            checked = checked,
            onCheckedChange = onSwitch
        )
    }
}


//@Composable
//@Preview(showBackground = true)
//fun SettingsBlock() {
//    OpTheme(darkTheme = true) {
//        OpBackground {
//            Column(modifier = Modifier.fillMaxWidth()) {
//                Column(
//                    modifier = Modifier
//                        .padding(
//                            top = LocalBorderPadding.current.calculateTopPadding()
//                        )
//                        .fillMaxWidth()
//                ) {
//                    SettingsBlockSwitch(
//                        text = stringResource(R.string.dark_mode),
//                        onSwitch = {})
//                    SettingsBlockButton(
//                        textColor = MaterialTheme.colorScheme.error,
//                        text = stringResource(R.string.sign_out),
//                        onClick = {}
//                    )
//                }
//            }
//        }
//    }
//}

fun Modifier.horizontalBorder(borderWidth: Dp, color: Color) =
    this.drawWithContent {
        drawContent()
        drawLine(
            color = color,
            start = Offset(0f, 0f),
            end = Offset(size.width, 0f),
            strokeWidth = borderWidth.toPx()
        )
        drawLine(
            color = color,
            start = Offset(0f, size.height),
            end = Offset(size.width, size.height),
            strokeWidth = borderWidth.toPx()
        )
    }
