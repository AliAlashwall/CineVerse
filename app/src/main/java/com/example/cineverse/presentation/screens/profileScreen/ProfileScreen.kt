package com.example.cineverse.presentation.screens.profileScreen

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.cineverse.R
import com.example.cineverse.presentation.components.ShortcutItem
import com.example.cineverse.presentation.designSystem.theme.CineVerseTheme
import com.example.cineverse.presentation.designSystem.theme.Theme
import com.example.cineverse.presentation.screens.profileScreen.components.SettingsContainer
import com.example.cineverse.presentation.screens.profileScreen.components.UserCard

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    profileViewModel: ProfileViewModel
) {

    val profileUiState by profileViewModel.profileUiState.collectAsStateWithLifecycle()

    ProfileScreenContainer(
        modifier = modifier,
        name = profileUiState.name ?: "",
        userName = profileUiState.userName ?: "",
        image = profileUiState.avatarPath ?: profileUiState.gravatar,
    )
}

@Composable
fun ProfileScreenContainer(
    modifier: Modifier = Modifier,
    name: String,
    userName: String,
    image: String,
) {
    Column(
        modifier = modifier
            .background(Theme.colors.backgroundScreen)
            .fillMaxSize()
            .padding(16.dp),
    ) {
        Text(
            text = stringResource(R.string.my_profile),
            style = Theme.textStyle.titleSm,
            color = Theme.colors.shadePrimary,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Start
        )
        Spacer(Modifier.height(33.dp))

        UserCard(
            name = name,
            userName = userName,
            image = image
        )
        Spacer(Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            ShortcutItem(
                icon = R.drawable.outline_history,
                text = stringResource(R.string.history),
                contentDescription = stringResource(R.string.history)
            )
            ShortcutItem(
                icon = R.drawable.due_tone_video_library,
                text = stringResource(R.string.my_collections),
                contentDescription = stringResource(R.string.my_collections)
            )
            ShortcutItem(
                icon = R.drawable.due_tone_star,
                text = stringResource(R.string.my_ratings),
                contentDescription = stringResource(R.string.my_ratings)
            )
        }

        Spacer(Modifier.height(24.dp))

        Text(
            text = stringResource(R.string.settings),
            style = Theme.textStyle.titleSm,
            color = Theme.colors.shadePrimary
        )
        Spacer(Modifier.height(12.dp))

        SettingsContainer()

    }
}

@Preview(
    showBackground = false, showSystemUi = false,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
private fun ProfileScreenPreview() {

    CineVerseTheme {
        ProfileScreenContainer(
            name = "Ali Gamal",
            userName = "@aligamal",
            image = ""
        )

    }

}