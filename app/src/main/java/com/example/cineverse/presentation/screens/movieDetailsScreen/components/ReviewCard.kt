package com.example.cineverse.presentation.screens.movieDetailsScreen.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.cineverse.R
import com.example.cineverse.domain.model.Review
import com.example.cineverse.presentation.designSystem.theme.CineVerseTheme
import com.example.cineverse.presentation.designSystem.theme.Theme

@Composable
fun ReviewCard(
    review: Review,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Theme.colors.backgroundCard)
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = review.avatarPath?.let { "https://image.tmdb.org/t/p/w200$it" }
                    ?: R.drawable.due_tone_image,
                contentDescription = stringResource(R.string.user_image),
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = R.drawable.due_tone_image),
                error = painterResource(id = R.drawable.due_tone_station)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(
                    text = review.author,
                    style = Theme.textStyle.bodyMdMedium,
                    color = Theme.colors.shadePrimary
                )
                Text(
                    text = "@${review.username}",
                    style = Theme.textStyle.bodySmRegular,
                    color = Theme.colors.shadeSecondary
                )
            }
        }
        val showFullText = remember { mutableStateOf(false) }

        val annotatedContent =
            buildAnnotatedString {
                val content = review.content
                if (content.length > 200) {
                    append(content.take(200))
                    withStyle(style = SpanStyle(color = Theme.colors.brandPrimary)) {
                        append("... ")
                        append("Read more")
                    }
                } else {
                    append(content)
                }
            }

        Text(
            text = if (showFullText.value) buildAnnotatedString { append(review.content) }
            else annotatedContent,
            style = Theme.textStyle.bodyMdMedium,
            color = Theme.colors.shadePrimary,
            maxLines = 5,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.clickable { showFullText.value = !showFullText.value }
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                val rating = (review.rating?.div(2))?.toInt() ?: 0
                repeat(5) { index ->
                    Icon(
                        painter = painterResource(
                            id = if (index < rating) R.drawable.yellow_star else R.drawable.outline_star
                        ),
                        contentDescription = null,
                        modifier = Modifier.size(16.dp),
                        tint = if (index < rating) Theme.colors.additionalPrimaryYellow else Theme.colors.shadeTertiary
                    )
                }
            }

            // Simple date display, ideally use a formatter
            Text(
                text = review.createdAt.split("T").firstOrNull() ?: "",
                style = Theme.textStyle.bodySmRegular,
                color = Theme.colors.shadeSecondary
            )
        }
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL,
)
@Composable
private fun ReviewCardPreview() {
    CineVerseTheme {
        ReviewCard(
            review = Review(
                id = "1",
                author = "John Doe",
                username = "johndoe",
                avatarPath = null,
                content = "This movie was fantastic! The storyline was gripping and the acting was top-notch. Highly recommend to everyone!",
                createdAt = "2024-06-01T12:34:56Z",
                rating = 8.0
            )
        )
    }
}