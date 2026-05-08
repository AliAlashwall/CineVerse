package com.example.cineverse.presentation.screens.movieDetailsScreen.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cineverse.R
import com.example.cineverse.domain.model.Review
import com.example.cineverse.presentation.components.ShowMoreRow
import com.example.cineverse.presentation.designSystem.theme.CineVerseTheme

@Composable
fun ReviewsSection(
    modifier: Modifier = Modifier,
    reviews: List<Review>,
    initialItemsCount: Int = 3,
) {
    if (reviews.isEmpty()) return
    var isExpanded by remember { mutableStateOf(false) }

    val displayedReviews = remember(reviews, isExpanded) {
        if (isExpanded) reviews else reviews.take(initialItemsCount)
    }


    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        ShowMoreRow(
            title = stringResource(id = R.string.top_reviews),
            shouldShowMoreButton = reviews.size > 3,
            handleShowMore = { isExpanded = true }
        )


        if (isExpanded) {
            reviews.forEach { review ->
                ReviewCard(review = review)
            }
        } else {
            displayedReviews.take(displayedReviews.size).forEach { review ->
                ReviewCard(review = review)
            }
        }
    }
}

@Preview(

    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL,
    showSystemUi = false, backgroundColor = 0xFFF7F7F7
)
@Composable
private fun ReviewsSectionPreview() {
    CineVerseTheme {
        ReviewsSection(
            reviews = listOf(
                Review(
                    id = "1",
                    author = "John Doe",
                    username = "johndoe",
                    avatarPath = "/https://secure.gravatar.com/avatar/992eef352126a53d7e141bf9e8707576.jpg",
                    content = "This movie was fantastic! The storyline was gripping and the acting was top-notch. Highly recommend to everyone!",
                    createdAt = "2024-06-01T12:34:56Z",
                    rating = 8.0
                ),
                Review(
                    id = "2",
                    author = "John Doe",
                    username = "johndoe",
                    avatarPath = "/https://secure.gravatar.com/avatar/992eef352126a53d7e141bf9e8707576.jpg",
                    content = "This movie was fantastic! The storyline was gripping and the acting was top-notch. Highly recommend to everyone!",
                    createdAt = "2024-06-01T12:34:56Z",
                    rating = 8.0
                ),
                Review(
                    id = "3",
                    author = "John Doe",
                    username = "johndoe",
                    avatarPath = "/https://secure.gravatar.com/avatar/992eef352126a53d7e141bf9e8707576.jpg",
                    content = "This movie was fantastic! The storyline was gripping and the acting was top-notch. Highly recommend to everyone!",
                    createdAt = "2024-06-01T12:34:56Z",
                    rating = 8.0
                ),
                Review(
                    id = "4",
                    author = "John Doe",
                    username = "johndoe",
                    avatarPath = "/https://secure.gravatar.com/avatar/992eef352126a53d7e141bf9e8707576.jpg",
                    content = "This movie was fantastic! The storyline was gripping and the acting was top-notch. Highly recommend to everyone!",
                    createdAt = "2024-06-01T12:34:56Z",
                    rating = 8.0
                ),
            ),
        )
    }
}