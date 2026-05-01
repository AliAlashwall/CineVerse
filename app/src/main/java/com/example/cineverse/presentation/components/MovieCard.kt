package com.example.cineverse.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.cineverse.domain.model.Movie
import com.example.cineverse.presentation.designSystem.theme.Theme


@Composable
fun MovieCard(
    movie: Movie,
    modifier: Modifier = Modifier,
    onMovieClicked: () -> Unit
) {
    Column(
        modifier = modifier.width(136.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Box(
            modifier = Modifier
                .clickable { onMovieClicked() }
                .fillMaxWidth()
                .aspectRatio(0.74f)
                .clip(RoundedCornerShape(12.dp))
        ) {

            MoviePoster(
                posterPath = movie.posterPath,
                modifier = Modifier.fillMaxSize()
            )

            RatingBadge(
                rating = movie.voteAverage.toString(),
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(8.dp)
            )
        }
        Text(
            text = movie.title,
            style = Theme.textStyle.bodyMdMedium,
            color = Theme.colors.shadeSecondary,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}