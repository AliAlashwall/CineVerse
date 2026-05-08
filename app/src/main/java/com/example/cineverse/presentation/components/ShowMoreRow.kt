package com.example.cineverse.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.cineverse.R
import com.example.cineverse.presentation.designSystem.theme.Theme

@Composable
fun ShowMoreRow(
    modifier: Modifier = Modifier,
    title: String,
    shouldShowMoreButton: Boolean,
    isExpanded: Boolean,
    handleShowMore: () -> Unit
) {
    val isReviewsExpanded by remember { mutableStateOf(isExpanded) }
    Row(
        modifier = modifier
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

        if (shouldShowMoreButton) {
            Text(
                text = stringResource(id = R.string.show_more),
                style = Theme.textStyle.bodyMdMedium,
                color = Theme.colors.brandPrimary,
                modifier = Modifier.clickable(enabled = !isReviewsExpanded) {
                    handleShowMore()
                }
            )
        }
    }
}
