package com.example.cineverse.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.cineverse.presentation.designSystem.theme.Theme

@Composable
fun ShortcutItem(
    icon: Int,
    text: String,
    contentDescription: String,
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(Theme.colors.backgroundCard)
            .padding(start = 10.dp, top = 10.dp, bottom = 10.dp, end = 12.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon(
                painter = painterResource(icon),
                contentDescription = contentDescription,
                tint = Theme.colors.brandPrimary,
                modifier = Modifier.size(16.dp)
            )
            Text(
                text = text,
                style = Theme.textStyle.labelMdMedium,
                color = Theme.colors.shadePrimary
            )
        }
    }
}