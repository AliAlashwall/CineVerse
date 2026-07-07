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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cineverse.domain.model.Movie
import com.example.cineverse.presentation.designSystem.theme.Theme


@Composable
fun MovieCard(
    movie: Movie,
    modifier: Modifier = Modifier,
    onMovieClicked: (Int) -> Unit
) {
    Column(
        modifier = modifier.width(136.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Box(
            modifier = Modifier
                .clickable { onMovieClicked(movie.id) }
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

@Preview
@Composable
private fun MovieCardPreview() {
   MovieCard(
       movie = Movie(
           adult = false,
           backdropPath = "/zfbjgQE1uSd9wiPTX4VzsLi0rGG.jpg",
           genreIds = listOf(
               18,
               80
           ),
           id = 2787,
           title = "The Shawshank Redemption",
           originalLanguage = "en",
           originalTitle = "The Shawshank Redemption",
           overview = "Imprisoned in the 1940s for the double murder of his wife and her lover, upstanding banker Andy Dufresne begins a new life at the Shawshank prison, where he puts his accounting skills to work for an amoral warden. During his long stretch in prison, Dufresne comes to be admired by the other inmates -- including an older prisoner named Red -- for his integrity and unquenchable sense of hope.",
           popularity = 48.5295,
           posterPath = "/9cqNxx0GxF0bflZmeSMuL5tnGzr.jpg",
           releaseDate = "1994-09-23",
           softcore = false,
           video = false,
           voteAverage = 7.5,
           voteCount = 30212
       ),
   ) { }
}