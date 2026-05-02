package com.example.cineverse.presentation.screens.movieDetailsScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.cineverse.R
import com.example.cineverse.presentation.components.CustomIconButton
import com.example.cineverse.presentation.components.CustomTextWithIcon
import com.example.cineverse.presentation.designSystem.theme.Theme

@Composable
fun MovieOverViewCard(
    movieName: String = "The Dark Knight",
    movieCategory: String = "Drama, Action, Crime, Thriller",
    movieRate: String = "8.5",
    movieDuration: String = "2h 32m",
    dateCreated: String = "2008, Jul 18",
    onPlayClicked: () -> Unit = {},
    onAddClicked: () -> Unit = {}
) {
    val rateLength = remember(movieRate) { if (movieRate.length > 3) 3 else movieRate.length }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(125.dp)
            .background(
                color = Theme.colors.backgroundCard,
                shape = RoundedCornerShape(16.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Row(
            Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(
                    text = stringResource(R.string.movie),
                    style = Theme.textStyle.labelMdMedium,
                    color = Theme.colors.brandPrimary
                )
                Text(
                    text = movieName,
                    style = Theme.textStyle.titleMd,
                    color = Theme.colors.shadePrimary
                )

                Text(
                    text = movieCategory,
                    style = Theme.textStyle.bodySmMedium,
                    color = Theme.colors.shadeSecondary
                )
                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    CustomTextWithIcon(
                        text = movieRate.take(rateLength),
                        iconId = R.drawable.due_tone_star,
                        iconColor = Theme.colors.additionalPrimaryYellow
                    )

                    CustomTextWithIcon(
                        text = movieDuration,
                        iconId = R.drawable.due_tone_clock,
                        iconColor = Theme.colors.shadeSecondary
                    )

                    CustomTextWithIcon(
                        text = dateCreated,
                        iconId = R.drawable.due_tone_calendar,
                        iconColor = Theme.colors.shadeSecondary
                    )

                }

            }

            Spacer(modifier = Modifier.weight(1f))

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                CustomIconButton(
                    iconId = R.drawable.due_tone_play,
                    contentDescription = stringResource(R.string.play_movie),
                    onClick = { onPlayClicked() },
                    buttonColor = Theme.colors.buttonPrimary,
                    iconColor = Color.Unspecified
                )

                CustomIconButton(
                    iconId = R.drawable.due_tone_add,
                    contentDescription = stringResource(R.string.play_movie),
                    onClick = { onAddClicked() },
                    buttonColor = Theme.colors.buttonSecondary,
                    iconColor = Theme.colors.buttonOnSecondary
                )

            }

        }
    }
}