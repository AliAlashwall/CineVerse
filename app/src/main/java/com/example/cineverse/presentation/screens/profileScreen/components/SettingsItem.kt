package com.example.cineverse.presentation.screens.profileScreen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cineverse.R
import com.example.cineverse.presentation.designSystem.theme.CineVerseTheme
import com.example.cineverse.presentation.designSystem.theme.Theme

@Composable
fun SettingsItem(
    icon: Int,
    text: String,
    iconColor: Color = Theme.colors.brandPrimary,
    textColor: Color = Theme.colors.shadePrimary,
    content: @Composable () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(vertical = 18.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Icon(
            painter = painterResource(icon),
            contentDescription = "Settings icon",
            modifier = Modifier.size(20.dp),
            tint = iconColor
        )

        Text(
            text = text,
            style = Theme.textStyle.bodyMdMedium,
            color = textColor
        )
        Spacer(Modifier.weight(1f))

        content()

    }
}

@Preview
@Composable
private fun SettingsItemPreview() {
    CineVerseTheme {
        SettingsItem(
            icon = R.drawable.due_tone_moon,
            text = "Dark Mode"
        ) {
            Text(
                text = "On",
                style = Theme.textStyle.bodyMdMedium,
                color = Theme.colors.shadePrimary
            )
        }
    }
}