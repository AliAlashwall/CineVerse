package com.example.cineverse.presentation.screens.homeScreen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.cineverse.R
import com.example.cineverse.presentation.designSystem.theme.Theme

@Composable
fun HomeHeader(
    userName: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.cine_verse_logo),
            contentDescription = stringResource(R.string.app_name),
            modifier = Modifier.size(32.dp),
            tint = Color.Unspecified
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(
            modifier = Modifier.clickable { onClick() }) {
            Text(
                text = stringResource(id = R.string.welcome),
                style = Theme.textStyle.bodySmRegular,
                color = Theme.colors.shadeSecondary
            )
            Text(
                text = userName,
                style = Theme.textStyle.titleSm,
                color = Theme.colors.shadePrimary
            )
        }
    }
}