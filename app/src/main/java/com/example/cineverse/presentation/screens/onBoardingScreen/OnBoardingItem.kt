package com.example.cineverse.presentation.screens.onBoardingScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.cineverse.R
import com.example.cineverse.presentation.designSystem.theme.Theme

@Composable
fun OnBoardingItem(
    modifier: Modifier = Modifier,
    image: Int,
    title: String,
    description: String,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        Spacer(Modifier.height(140.dp))

        Image(
            painter = painterResource(image),
            contentDescription = stringResource(R.string.on_boarding_1_image),
            modifier = Modifier
                .size(350.dp, 510.dp)
                .padding(horizontal = 16.dp),
            contentScale = ContentScale.FillBounds
        )

        Spacer(Modifier.height(32.dp))

        Text(
            text = title,
            color = Theme.colors.shadePrimary,
            style = Theme.textStyle.titleMd,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            textAlign = TextAlign.Center
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = description,
            color = Theme.colors.shadeSecondary,
            style = Theme.textStyle.bodyMdMedium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            textAlign = TextAlign.Center,
        )

//        Spacer(Modifier.height(132.dp))
        Spacer(Modifier.weight(1f))
    }
}