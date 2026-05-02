package com.example.cineverse.presentation.screens.homeScreen.components

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.compose.ui.zIndex
import com.example.cineverse.domain.model.Movie
import com.example.cineverse.domain.model.mockMoviesList
import com.example.cineverse.presentation.designSystem.theme.CineVerseTheme
import kotlinx.coroutines.delay
import kotlin.math.absoluteValue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeHeaderCarousel(
    modifier: Modifier = Modifier,
    moviesList: List<Movie>,
    onClickMovie: (Movie) -> Unit
) {
    val pagerState = rememberPagerState(
        initialPage = 1,
        pageCount = { moviesList.size }
    )

    LaunchedEffect(pagerState) {
        while (true) {
            delay(3000)
            if (!pagerState.isScrollInProgress && moviesList.isNotEmpty()) {
                val nextPage = (pagerState.currentPage + 1) % moviesList.size
                pagerState.animateScrollToPage(
                    nextPage,
                    animationSpec = spring(
                        stiffness = Spring.StiffnessVeryLow,
                        dampingRatio = Spring.DampingRatioLowBouncy
                    )
                )
            }
        }
    }

    HorizontalPager(
        state = pagerState,
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 22.dp),
        pageSpacing = (-40).dp,
        verticalAlignment = Alignment.CenterVertically,
        beyondViewportPageCount = 1
    ) { page ->
        val item = moviesList[page]
        val pageOffset =
            ((pagerState.currentPage - page) + pagerState.currentPageOffsetFraction)
                .absoluteValue.coerceIn(0f, 1f)

        val animatedHeight = androidx.compose.ui.unit.lerp(250.dp, 230.dp, 1f - pageOffset)
        val animatedWidth = androidx.compose.ui.unit.lerp(360.dp, 312.dp, 1f - pageOffset)

        val cardAlpha = lerp(0.6f, 1f, 1f - pageOffset)

        Box(
            modifier = Modifier
                .height(270.dp)
                .zIndex(1f - pageOffset)
        ) {
            HomeCarouselItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(BiasAlignment(0f, pageOffset - 1f))
                    .alpha(cardAlpha)
                    .size(width = animatedWidth, height = animatedHeight),
                posterPath = item.posterPath,
                title = item.title,
                description = item.title,
                rate = item.voteAverage.toString(),
                onClick = { onClickMovie(item) }
            )
        }
    }
}


@Preview
@Composable
private fun HomeHeaderCarouselPreview() {
    CineVerseTheme {
        HomeHeaderCarousel(
            moviesList = mockMoviesList,
            onClickMovie = {}
        )
    }
}