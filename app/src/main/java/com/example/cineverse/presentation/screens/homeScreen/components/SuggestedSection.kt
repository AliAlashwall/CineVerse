package com.example.cineverse.presentation.screens.homeScreen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.cineverse.R
import com.example.cineverse.domain.model.Movie
import com.example.cineverse.presentation.components.MovieCard
import com.example.cineverse.presentation.designSystem.theme.Theme


@Composable
fun SuggestedSection(
    modifier: Modifier = Modifier,
    title: String,
    moviesList: List<Movie>,
    onShowMoreClicked: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                style = Theme.textStyle.titleSm,
                color = Theme.colors.shadePrimary
            )

            Text(
                text = stringResource(id = R.string.show_more),
                style = Theme.textStyle.bodyMdMedium,
                color = Theme.colors.brandPrimary,
                modifier = Modifier.clickable { onShowMoreClicked() }
            )
        }
        LazyRow(
            contentPadding = PaddingValues(horizontal = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(moviesList) { movie ->
                MovieCard(
                    movie = movie,
                )
            }
        }
    }
}