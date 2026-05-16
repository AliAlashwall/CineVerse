package com.example.cineverse.presentation.screens.profileScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.cineverse.R
import com.example.cineverse.presentation.designSystem.theme.Theme

@Composable
fun UserCard(
    name: String,
    userName: String,
    image: String
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Theme.colors.backgroundCard,
                shape = RoundedCornerShape(12.dp)
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(17.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(image)
                    .crossfade(true)
                    .build(),

                contentDescription = "Movie Poster",

                contentScale = ContentScale.Crop,

                placeholder = painterResource(R.drawable.loading_dark),
                error = painterResource(R.drawable.due_tone_station),

                modifier = Modifier
                    .size(56.dp)
                    .clip(CircleShape)
            )


            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = name,
                    style = Theme.textStyle.bodyLgMedium,
                    color = Theme.colors.shadePrimary
                )
                Text(
                    text = userName,
                    style = Theme.textStyle.bodySmMedium,
                    color = Theme.colors.shadeSecondary
                )
            }
            Spacer(Modifier.weight(1f))

            Icon(
                painter = painterResource(R.drawable.outline_alt_arrow_right),
                contentDescription = stringResource(R.string.forward_arrow),
                modifier = Modifier.size(20.dp),
                tint = Theme.colors.shadeTertiary
            )
        }
    }
}