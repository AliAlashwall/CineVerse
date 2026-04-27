package com.example.cineverse.presentation.screens.homeScreen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.cineverse.presentation.components.MoviePoster
import com.example.cineverse.presentation.components.RatingBadge
import com.example.cineverse.presentation.designSystem.theme.Theme

@Composable
fun HomeCarouselItem(
    modifier: Modifier = Modifier,
    posterPath: String,
    title: String,
    description: String,
    rate: String,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier.clickable{ onClick()}
                .fillMaxWidth(0.86f)
                .aspectRatio(1.56f)
                .clip(RoundedCornerShape(24.dp))
        ) {
            MoviePoster(
                posterPath = posterPath,
                modifier = Modifier.fillMaxSize()
            )
            RatingBadge(
                rating = rate,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(16.dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = title,
            style = Theme.textStyle.bodyMdMedium,
            color = Theme.colors.shadePrimary,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = description,
            style = Theme.textStyle.bodySmRegular,
            color = Theme.colors.shadeSecondary,
            textAlign = TextAlign.Center
        )
    }
}