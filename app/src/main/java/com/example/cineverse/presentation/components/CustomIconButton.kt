package com.example.cineverse.presentation.components

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun CustomIconButton(
    modifier: Modifier = Modifier,
    iconId: Int, contentDescription: String, onClick: () -> Unit,
    buttonColor: Color,
    iconColor: Color,
) {
    IconButton(
        onClick = { onClick() },
        modifier = modifier.size(40.dp),
        colors = IconButtonDefaults.iconButtonColors(buttonColor),
        shape = RoundedCornerShape(12.dp)
    ) {
        Icon(
            painterResource(iconId),
            contentDescription = contentDescription,
            modifier = Modifier.size(20.dp),
            tint = iconColor
        )
    }

}