package com.example.cineverse.presentation.screens.profileScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.cineverse.R
import com.example.cineverse.presentation.designSystem.theme.Theme

@Composable
fun SettingsContainer() {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(Theme.colors.backgroundCard)
            .fillMaxWidth()
    ) {
        Column(Modifier.padding( horizontal = 16.dp)) {

            SettingsItem(
                icon = R.drawable.due_tone_moon,
                text = stringResource(R.string.dark_mode),
                content = {
                    Switch(
                        checked = Theme.state.isDark,
                        onCheckedChange = { },
                        colors = SwitchDefaults.colors(
                            checkedTrackColor = Theme.colors.brandPrimary,
                            uncheckedTrackColor = Theme.colors.shadePrimary
                        ),
                        modifier = Modifier.size(40.dp, 24.dp)
                    )
                }
            )

            HorizontalDivider(color = Theme.colors.strokePrimary)


            SettingsItem(
                icon = R.drawable.due_tone_language,
                text = stringResource(R.string.language)
            ) {
                Icon(
                    painter = painterResource(R.drawable.outline_alt_arrow_right),
                    contentDescription = stringResource(R.string.change_language),
                    tint = Theme.colors.shadeTertiary,
                    modifier = Modifier.size(20.dp)
                )
            }
            HorizontalDivider(color = Theme.colors.strokePrimary)

            SettingsItem(
                icon = R.drawable.due_tone_color_switch,
                text = stringResource(R.string.content_preferences)
            ) {
                Icon(
                    painter = painterResource(R.drawable.outline_alt_arrow_right),
                    contentDescription = stringResource(R.string.change_language),
                    tint = Theme.colors.shadeTertiary,
                    modifier = Modifier.size(20.dp)
                )
            }

            HorizontalDivider(color = Theme.colors.strokePrimary)

            SettingsItem(
                icon = R.drawable.due_tone_logout,
                text = stringResource(R.string.logout),
                iconColor = Theme.colors.additionalPrimaryRed,
                textColor = Theme.colors.additionalPrimaryRed
            ) {
                Icon(
                    painter = painterResource(R.drawable.outline_alt_arrow_right),
                    contentDescription = stringResource(R.string.change_language),
                    tint = Theme.colors.additionalPrimaryRed,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}
