package com.example.cineverse.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
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
fun CineVerseErrorScreen(
    modifier: Modifier = Modifier,
    onTryAgain: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Theme.colors.backgroundScreen)
            .padding(horizontal = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(80.dp)
                .background(color = Theme.colors.additionalSecondaryRed, shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.due_tone_station),
                contentDescription = stringResource(R.string.oops_no_internet),
                tint = Theme.colors.additionalPrimaryRed,
                modifier = Modifier.size(32.dp)
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = stringResource(id = R.string.oops_no_internet),
            style = Theme.textStyle.titleXl,
            color = Theme.colors.shadePrimary,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(id = R.string.offline_reconnect),
            style = Theme.textStyle.bodyMdRegular,
            color = Theme.colors.shadeSecondary,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 24.dp)
        )
        Spacer(modifier = Modifier.height(40.dp))
        CustomButton(
            text = stringResource(id = R.string.try_again),
            onClicked = onTryAgain,
            modifier = Modifier.fillMaxWidth(),
            focusButtonColor = Theme.colors.buttonPrimary,
            focusTextColor = Theme.colors.buttonOnPrimary
        )
    }
}

@Preview
@Composable
private fun CineVerseErrorScreenPreview() {
    CineVerseTheme {
        CineVerseErrorScreen(onTryAgain = {})
    }
}