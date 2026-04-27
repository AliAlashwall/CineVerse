package com.example.cineverse.presentation.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.cineverse.R
import com.example.cineverse.presentation.designSystem.theme.CineVerseTheme

@Composable
fun MoviePoster(
    posterPath: String?,
    modifier: Modifier = Modifier
) {
    val imageUrl = "https://image.tmdb.org/t/p/w500$posterPath"

    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(true)
            .build(),

        contentDescription = "Movie Poster",

        contentScale = ContentScale.Crop,

        placeholder = painterResource(R.drawable.loading_dark),
        error = painterResource(R.drawable.due_tone_station),

        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
    )
}


@Preview
@Composable
private fun MoviePosterPreview() {
    CineVerseTheme {
        MoviePoster(posterPath = "/vZloFAK7NmvMGKE7VkF5UHaz0I.jpg")
    }

}