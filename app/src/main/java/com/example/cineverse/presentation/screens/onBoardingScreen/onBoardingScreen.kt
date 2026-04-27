package com.example.cineverse.presentation.screens.onBoardingScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cineverse.R
import com.example.cineverse.presentation.designSystem.theme.CineVerseTheme
import com.example.cineverse.presentation.designSystem.theme.Theme
import kotlinx.coroutines.launch

@Composable
fun OnBoardingScreen(
    onGetStartedClicked: () -> Unit
) {
    val pagesList = listOf(
        Triple(
            R.drawable.on_boarding_1,
            stringResource(R.string.welcome_to_your_movie_universe),
            stringResource(R.string.discover_track_and_rate_your_favorite_movies_series)
        ),
        Triple(
            R.drawable.on_boarding_2,
            stringResource(R.string.track_everything),
            stringResource(R.string.your_watchlist_your_ratings_all_in_one_place)
        ), Triple(
            R.drawable.on_boarding_3,
            stringResource(R.string.personalized_recommendations),
            stringResource(R.string.answer_fun_questions_to_get_handpicked_recommendations)
        )
    )
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { pagesList.size })
    val scope = rememberCoroutineScope()


    Box(
        contentAlignment = Alignment.BottomCenter,
        modifier = Modifier
            .fillMaxSize()
            .background(Theme.colors.backgroundScreen)
    ) {


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(224.dp)
                .padding(16.dp)
                .background(
                    Theme.colors.backgroundBottomSheet,
                    RoundedCornerShape(16.dp)
                ),
            contentAlignment = Alignment.BottomCenter,
            content = {}
        )

        HorizontalPager(state = pagerState) { page ->
//            OnBoardingItem(
//                image = pagesList[page].first,
//                title = pagesList[page].second,
//                description = pagesList[page].third,
//            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                Spacer(Modifier.weight(1f))

                Image(
                    painter = painterResource(pagesList[page].first),
                    contentDescription = stringResource(R.string.on_boarding_1_image),
                    modifier = Modifier
                        .size(350.dp, 510.dp)
                        .padding(horizontal = 16.dp),
                    contentScale = ContentScale.FillBounds
                )

                Spacer(Modifier.height(42.dp))

                Text(
                    text = pagesList[page].second,
                    color = Theme.colors.shadePrimary,
                    style = Theme.textStyle.titleMd,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(25.dp)
                        .padding(horizontal = 8.dp),
                    textAlign = TextAlign.Center
                )

                Spacer(Modifier.height(8.dp))

                Text(
                    text = pagesList[page].third,
                    color = Theme.colors.shadeSecondary,
                    style = Theme.textStyle.bodyMdMedium,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(38.dp)
                        .padding(horizontal = 8.dp),
                    textAlign = TextAlign.Center,
                    maxLines = 2
                )

                Spacer(Modifier.height(96.dp))
            }
        }

        Row(
            Modifier
                .fillMaxWidth()
                .padding(bottom = 40.dp)
                .height(48.dp)
                .padding(horizontal = 40.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (pagerState.currentPage > 0) {
                Button(
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage - 1)
                        }
                    },
                    modifier = Modifier.size(48.dp),
                    contentPadding = PaddingValues(0.dp),
                    colors = ButtonDefaults.buttonColors(Theme.colors.buttonSecondary),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.outline_alt_arrow_left),
                        contentDescription = stringResource(R.string.back_arrow),
                        modifier = Modifier
                            .size(20.dp),
                        tint = Theme.colors.shadePrimary

                    )
                }
            }
            Spacer(Modifier.weight(1f))

            Button(
                onClick = {
                    if (pagerState.currentPage < pagesList.size - 1) {
                        scope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    } else {
                        onGetStartedClicked()
                    }
                },
                modifier = if (pagerState.currentPage != 2) Modifier.size(48.dp) else Modifier,
                contentPadding = if (pagerState.currentPage == 2) {
                    PaddingValues(
                        top = 14.dp,
                        bottom = 14.dp,
                        start = 24.dp,
                        end = 16.dp
                    )
                } else {
                    PaddingValues(0.dp)
                },
                colors = ButtonDefaults.buttonColors(Theme.colors.buttonPrimary),
                shape = RoundedCornerShape(16.dp)
            ) {
                if (pagerState.currentPage == 2) {
                    Text(
                        text = stringResource(R.string.get_started),
                        style = Theme.textStyle.bodyMdMedium,
                        color = Theme.colors.buttonOnPrimary
                    )
                    Spacer(Modifier.width(8.dp))
                }
                Icon(
                    painter = painterResource(R.drawable.outline_alt_arrow_right),
                    contentDescription = stringResource(R.string.back_arrow),
                    modifier = Modifier
                        .size(20.dp),
                    tint = Theme.colors.backgroundScreen
                )
            }
        }

    }
}

@Preview(showSystemUi = false, showBackground = true)
@Composable
private fun OnBoardingScreenPreview() {
    CineVerseTheme {
        OnBoardingScreen(onGetStartedClicked = {})
    }
}
