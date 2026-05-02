package com.example.cineverse.presentation.screens.movieDetailsScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.cineverse.R
import com.example.cineverse.domain.model.Cast
import com.example.cineverse.presentation.components.MoviePoster
import com.example.cineverse.presentation.designSystem.theme.Theme

@Composable
fun StarCastSection(
    castList: List<Cast>,
    modifier: Modifier = Modifier
) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp), modifier = modifier) {
        Text(
            text = stringResource(R.string.star_cast),
            style = Theme.textStyle.titleSm,
            color = Theme.colors.shadePrimary,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Start
        )


        LazyHorizontalGrid(
            modifier = Modifier
                .height(140.dp)
                .fillMaxWidth(),
            rows = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            items(items = castList, key = { it.id }) { cast ->
                Box(
                    modifier = Modifier
                        .size(200.dp, 64.dp)
                        .background(
                            color = Theme.colors.backgroundCard,
                            shape = RoundedCornerShape(12.dp)
                        )
                ) {
                    Row {

                        MoviePoster(
                            posterPath = cast.profilePath,
                            modifier = Modifier.size(64.dp)
                        )

                        Column(
                            modifier = Modifier
                                .padding(vertical = 13.dp, horizontal = 12.dp),
                            verticalArrangement = Arrangement.spacedBy(4.dp),
                            horizontalAlignment = Alignment.Start
                        ) {
                            Text(
                                text = cast.name,
                                style = Theme.textStyle.bodyMdMedium,
                                color = Theme.colors.shadePrimary,
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 1
                            )
                            Text(
                                text = cast.character,
                                style = Theme.textStyle.bodySmRegular,
                                color = Theme.colors.shadeSecondary,
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 1
                            )
                        }
                    }
                }
            }
        }
    }
}