package com.example.cineverse.presentation.screens.profileScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.cineverse.presentation.designSystem.theme.CineVerseTheme
import com.example.cineverse.presentation.designSystem.theme.Theme

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    profileViewModel: ProfileViewModel
) {

    val profileUiState by profileViewModel.profileUiState.collectAsStateWithLifecycle()

    ProfileScreenContainer(
        modifier = modifier,
        profileUiState = profileUiState
    )
}

@Composable
fun ProfileScreenContainer(
    modifier: Modifier = Modifier,
    profileUiState: ProfileUiState
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(22.dp)
    ) {
        Text(
            text = "Profile Screen",
            style = Theme.textStyle.titleLg,
            color = Theme.colors.brandPrimary,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Start
        )


        Text(
            text = "sessionId: ${profileUiState.sessionId}",
            style = Theme.textStyle.titleSm,
            color = Theme.colors.buttonOnSecondary,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Start
        )
        Text(
            text = "userName: ${profileUiState.userName}",
            style = Theme.textStyle.titleSm,
            color = Theme.colors.buttonOnSecondary,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Start
        )
        Text(
            text = "name: ${profileUiState.name}",
            style = Theme.textStyle.titleSm,
            color = Theme.colors.buttonOnSecondary,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Start
        )
        Text(
            text = "avatarPath: ${profileUiState.avatarPath}",
            style = Theme.textStyle.titleSm,
            color = Theme.colors.buttonOnSecondary,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Start
        )
        Text(
            text = "gravatar: ${profileUiState.gravatar}",
            style = Theme.textStyle.titleSm,
            color = Theme.colors.buttonOnSecondary,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Start
        )

    }
}

@Preview
@Composable
private fun ProfileScreenPreview() {
     
    CineVerseTheme {
        ProfileScreenContainer(
            profileUiState = ProfileUiState()
        )

    }

}