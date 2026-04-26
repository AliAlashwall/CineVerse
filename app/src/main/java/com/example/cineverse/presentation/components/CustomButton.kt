package com.example.cineverse.presentation.components


import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cineverse.presentation.designSystem.theme.CineVerseTheme
import com.example.cineverse.presentation.designSystem.theme.Theme

@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    onClicked: () -> Unit,
    enable: Boolean = true,
    text: String,
    focusButtonColor: Color = Theme.colors.buttonSecondary,
    unFocusButtonColor: Color = Theme.colors.buttonDisabled,
    focusTextColor: Color = Theme.colors.buttonOnSecondary,
    unFocusTextColor: Color = Theme.colors.buttonOnDisabled,
    textStyle: TextStyle = Theme.textStyle.bodyMdMedium
) {
    Button(
        onClick = { onClicked() },
        shape = RoundedCornerShape(16.dp),
        colors = if (enable) ButtonDefaults.buttonColors(
            focusButtonColor
        )
        else ButtonDefaults.buttonColors(
            unFocusButtonColor,
        ),
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp),
        contentPadding = PaddingValues(0.dp),
    ) {
        Text(
            text = text,
            style = textStyle,
            color = if (enable) focusTextColor else unFocusTextColor,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
private fun CustomButtonPreview() {
    CineVerseTheme {
        CustomButton(
            onClicked = {},
            text = "Login",
            enable = false
        )

    }
}