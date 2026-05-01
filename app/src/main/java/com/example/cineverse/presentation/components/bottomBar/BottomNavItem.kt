package com.example.cineverse.presentation.components.bottomBar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cineverse.R
import com.example.cineverse.presentation.designSystem.theme.CineVerseTheme
import com.example.cineverse.presentation.designSystem.theme.Theme


@Composable
fun BottomNavItem(
    unSelectedIcon: Int,
    selectedIcon: Int,
    label: String,
    isSelected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier.clickable{ onClick() },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Icon(
            painter = if (isSelected) painterResource(id = selectedIcon) else painterResource(id = unSelectedIcon),
            contentDescription = label,
            modifier = Modifier.size(24.dp),
            tint = if (isSelected) Theme.colors.brandPrimary else Theme.colors.shadeSecondary
        )

        Text(
            text = label,
            style = if (isSelected) Theme.textStyle.labelMdSemiBold else Theme.textStyle.labelMdRegular,
            color = if (isSelected) Theme.colors.brandPrimary else Theme.colors.shadeSecondary
        )
    }
}

@Preview
@Composable
private fun ItemPreview() {
    CineVerseTheme {
        BottomNavItem(
            selectedIcon = R.drawable.due_tone_home,
            unSelectedIcon = R.drawable.outline_home,
            label = "Home",
            isSelected = false,
            onClick = {}
        )
    }
}