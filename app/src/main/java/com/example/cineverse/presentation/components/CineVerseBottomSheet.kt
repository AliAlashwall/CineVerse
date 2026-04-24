package com.example.cineverse.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cineverse.R
import com.example.cineverse.presentation.designSystem.theme.CineVerseTheme
import com.example.cineverse.presentation.designSystem.theme.Theme

@Composable
fun CineVerseBottomSheet(
    mainText: String,
    secondaryText: String,
    primaryButtonText: String,
    modifier: Modifier = Modifier,
    onCancelClicked: () -> Unit = {},
    onPrimaryButtonClicked: () -> Unit = {},
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(horizontal = 16.dp)
    ) {
        Spacer(Modifier.height(35.dp))
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(64.dp)
                .background(color = Theme.colors.brandTertiary, shape = RoundedCornerShape(50.dp)),
        ) {
            Icon(
                painterResource(R.drawable.due_tone_link_minimalistic),
                contentDescription = stringResource(R.string.go_to_website),
                modifier = Modifier.size(28.dp),
                tint = Theme.colors.brandPrimary
            )
        }

        Spacer(Modifier.height(16.dp))

        Text(
            text = mainText,
            style = Theme.textStyle.titleSm,
            color = Theme.colors.shadePrimary,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = secondaryText,
            style = Theme.textStyle.bodySmMedium,
            color = Theme.colors.shadeSecondary,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = { onCancelClicked() },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(Theme.colors.buttonSecondary),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(
                    text = stringResource(R.string.cancel),
                    style = Theme.textStyle.bodyMdMedium,
                    color = Theme.colors.buttonOnSecondary,
                )
            }

            Spacer(Modifier.width(12.dp))

            Button(
                onClick = { onPrimaryButtonClicked() },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(Theme.colors.buttonPrimary),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(
                    text = primaryButtonText,
                    style = Theme.textStyle.bodyMdMedium,
                    color = Theme.colors.buttonOnPrimary,
                )
            }
        }
        Spacer(Modifier.height(16.dp))
    }
}


@Preview
@Composable
private fun CineVerseBottomSheetPreview() {
    CineVerseTheme {
        CineVerseBottomSheet(
            mainText = "join CineVerse",
            secondaryText = "Join CineVerse to get personalized movie recommendations, create watch lists.",
            primaryButtonText = "Go to website"

        )
    }
}