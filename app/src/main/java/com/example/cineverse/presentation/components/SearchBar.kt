package com.example.cineverse.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cineverse.R
import com.example.cineverse.presentation.designSystem.theme.CineVerseTheme
import com.example.cineverse.presentation.designSystem.theme.Theme

@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onClick: () -> Unit = {},
    onBackClicked: () -> Unit = {},
    showBackArrow: Boolean
) {
    val focusManager = LocalFocusManager.current
    if (!showBackArrow) focusManager.clearFocus()
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp)
    ) {
        if (showBackArrow) {
            IconButton(
                onClick = { onBackClicked() }
            ) {
                Icon(
                    painter = painterResource(R.drawable.outline_arrow_left),
                    contentDescription = stringResource(R.string.back_arrow),
                    modifier = Modifier.size(24.dp),
                    tint = Theme.colors.shadePrimary
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
        }
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(Theme.colors.backgroundCard)
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxSize()
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.outline_search),
                    contentDescription = stringResource(R.string.search_icon),
                    tint = Theme.colors.shadeTertiary,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                BasicTextField(
                    value = query,
                    onValueChange = onQueryChange,
                    modifier = Modifier
                        .weight(1f)
                        .onFocusChanged { focusState ->
                            if (focusState.isFocused) {
                                onClick()
                            }

                        },
                    textStyle = Theme.textStyle.bodyMdRegular.copy(color = Theme.colors.shadePrimary),
                    cursorBrush = SolidColor(Theme.colors.buttonPrimary),
                    decorationBox = { innerTextField ->
                        if (query.isEmpty()) {
                            Text(
                                text = "Search...",
                                style = Theme.textStyle.bodyMdRegular,
                                color = Theme.colors.shadeTertiary
                            )
                        }
                        innerTextField()
                    }
                )
                Icon(
                    painter = painterResource(id = R.drawable.outline_microphone),
                    contentDescription = null,
                    tint = Theme.colors.shadeTertiary,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}


@Preview
@Composable
private fun SearchBarPreview() {
    CineVerseTheme {
        SearchBar(
            query = "",
            onQueryChange = {},
            onClick = {},
            onBackClicked = {},
            showBackArrow = false
        )
    }
}