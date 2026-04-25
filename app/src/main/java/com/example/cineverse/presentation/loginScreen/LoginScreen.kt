package com.example.cineverse.presentation.loginScreen

import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.cineverse.R
import com.example.cineverse.presentation.components.AnimatedLoading
import com.example.cineverse.presentation.components.CineVerseBottomSheet
import com.example.cineverse.presentation.components.CustomButton
import com.example.cineverse.presentation.designSystem.theme.CineVerseTheme
import com.example.cineverse.presentation.designSystem.theme.Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel,
    modifier: Modifier = Modifier
) {
    val authUiState by loginViewModel.authUiState.collectAsStateWithLifecycle()

    val loginResponseUiState by loginViewModel.loginResponse.collectAsStateWithLifecycle()
    val context = LocalContext.current


    Box {
        LoginScreenContainer(
            modifier = modifier,
            username = authUiState.username,
            onUsernameChanged = { loginViewModel.onUsernameChanged(it) },
            onPasswordChanged = { loginViewModel.onPasswordChanged(it) },
            password = authUiState.password,
            onLoginClicked = { loginViewModel.login() },
            onLoginAsGuestClicked = { loginViewModel.joinAsGuest() },
            onShowResetBottomSheet = { loginViewModel.onShowResetPSBottomSheet() },
            onShowSignUpBottomSheet = { loginViewModel.onShowSignUpBottomSheet() },
        )
        when (loginResponseUiState) {
            is Result.Loading -> {
                Box(Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f)),
                    contentAlignment = Alignment.Center) {
                    AnimatedLoading(
                        modifier = Modifier.size(80.dp),
                        tintColor = Theme.colors.shadePrimary,
                    )
                }
            }

            is Result.Success -> {
                //navigate to home
            }

            is Result.Error -> {
                Toast.makeText(
                    context,
                    (loginResponseUiState as Result.Error).message,
                    Toast.LENGTH_SHORT
                ).show()
            }

            is Result.Empty -> {}
        }

    }

    if (authUiState.showResetPSBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = { loginViewModel.onDismissBottomSheet() },
            containerColor = Theme.colors.backgroundScreen,
            modifier = Modifier.padding(horizontal = 12.dp)
        ) {
            CineVerseBottomSheet(
                mainText = stringResource(R.string.reset_your_password),
                secondaryText = stringResource(R.string.forget_password_description),
                onCancelClicked = { loginViewModel.onDismissBottomSheet() },
                onPrimaryButtonClicked = {
                    val url = "https://www.themoviedb.org/reset-password?language=it-VA"

                    val customTabsIntent = CustomTabsIntent.Builder().build()
                    customTabsIntent.launchUrl(context, url.toUri())
                    loginViewModel.onDismissBottomSheet()
                }
            )
        }
    }

    if (authUiState.showSignUpBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = { loginViewModel.onDismissBottomSheet() },
            containerColor = Theme.colors.backgroundScreen,
            modifier = Modifier.padding(horizontal = 12.dp)
        ) {
            CineVerseBottomSheet(
                mainText = stringResource(R.string.join_cineverse),
                secondaryText = stringResource(R.string.let_s_get_you_set_up_we_ll_take_you_to_the_website_to_create_your_account),
                onCancelClicked = { loginViewModel.onDismissBottomSheet() },
                onPrimaryButtonClicked = {
                    val url = "https://www.themoviedb.org/signup"

                    val customTabsIntent = CustomTabsIntent.Builder().build()
                    customTabsIntent.launchUrl(context, url.toUri())
                    loginViewModel.onDismissBottomSheet()
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreenContainer(
    modifier: Modifier = Modifier,
    username: String = "",
    password: String = "",
    onUsernameChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onLoginClicked: () -> Unit,
    onLoginAsGuestClicked: () -> Unit,
    onShowResetBottomSheet: () -> Unit,
    onShowSignUpBottomSheet: () -> Unit,
) {
    val passwordHiddenVisibility = rememberSaveable { mutableStateOf(false) }
    val passwordFocusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
    val customTextFieldColor = TextFieldDefaults.colors(
        focusedContainerColor = Theme.colors.strokePrimary,
        unfocusedContainerColor = Theme.colors.strokePrimary,
        focusedTextColor = Theme.colors.shadePrimary,
        unfocusedTextColor = Theme.colors.shadePrimary,
        focusedLabelColor = Theme.colors.strokePrimary,
        unfocusedLabelColor = Theme.colors.strokePrimary,
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Theme.colors.backgroundScreen)
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(80.dp))
        Image(
            painterResource(R.drawable.cine_verse_logo),
            contentDescription = stringResource(R.string.cine_verse_logo),
            modifier = Modifier.size(104.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = stringResource(R.string.welcome_back_to_cineverse),
            style = Theme.textStyle.titleLg,
            color = Theme.colors.shadePrimary,
        )
        Spacer(modifier = Modifier.height(48.dp))

        Text(
            text = stringResource(R.string.username),
            style = Theme.textStyle.bodyMdRegular,
            color = Theme.colors.shadeSecondary,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Start
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = username,
            onValueChange = { onUsernameChanged(it) },
            shape = RoundedCornerShape(16.dp),
            colors = customTextFieldColor,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { passwordFocusRequester.requestFocus() }
            ),
            placeholder = {
                Text(
                    text = stringResource(R.string.enter_your_email_or_username),
                    style = Theme.textStyle.bodyMdRegular,
                    color = Theme.colors.shadeTertiary,
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(passwordFocusRequester),
            leadingIcon = {
                Icon(
                    painterResource(R.drawable.outline_user),
                    contentDescription = stringResource(R.string.username),
                    tint = Theme.colors.shadeTertiary,
                    modifier = Modifier.size(20.dp)
                )
            }
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(R.string.password),
            style = Theme.textStyle.bodyMdRegular,
            color = Theme.colors.shadeSecondary,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Start
        )
        Spacer(modifier = Modifier.height(8.dp))


        TextField(
            value = password,
            onValueChange = { onPasswordChanged(it) },
            placeholder = {
                Text(
                    text = stringResource(R.string.enter_your_password),
                    style = Theme.textStyle.bodyMdRegular,
                    color = Theme.colors.shadeTertiary,
                )
            },
            shape = RoundedCornerShape(16.dp),
            colors = customTextFieldColor,

            leadingIcon = {
                Icon(
                    painterResource(R.drawable.outline_lock),
                    contentDescription = stringResource(R.string.password),
                    tint = Theme.colors.shadeTertiary,
                    modifier = Modifier.size(20.dp)
                )
            },
            trailingIcon = {
                IconButton(onClick = {
                    passwordHiddenVisibility.value = !passwordHiddenVisibility.value
                }) {
                    Icon(
                        painter = if (!passwordHiddenVisibility.value) {
                            painterResource(R.drawable.eye_closed)
                        } else {
                            painterResource(R.drawable.icon_eye)
                        },
                        contentDescription = stringResource(R.string.password),
                        tint = Theme.colors.shadeTertiary,
                        modifier = Modifier.size(20.dp)
                    )
                }
            },
            visualTransformation = if (!passwordHiddenVisibility.value) {
                PasswordVisualTransformation()
            } else {
                VisualTransformation.None
            },
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(passwordFocusRequester),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Password
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus() // close keyboard
                    // login functionality
                }
            )
        )

        Spacer(Modifier.height(8.dp))

        Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {
            Text(
                text = stringResource(R.string.forgot_password),
                color = Theme.colors.shadeSecondary,
                style = Theme.textStyle.bodyMdRegular,
                modifier = Modifier.clickable { onShowResetBottomSheet() }
            )
        }
        Spacer(Modifier.height(20.dp))

        CustomButton(
            onClicked = { onLoginClicked() },
            text = stringResource(R.string.login),
            enable = username.isNotBlank() && password.isNotBlank()
        )
        Spacer(Modifier.height(20.dp))

        CustomButton(
            onClicked = { onLoginAsGuestClicked() },
            text = stringResource(R.string.join_as_guest),
        )

        Spacer(Modifier.weight(1f))

        CustomButton(
            onClicked = { onShowSignUpBottomSheet() },
            text = stringResource(R.string.create_a_new_account),
            modifier = Modifier.size(170.dp, 40.dp),
            textStyle = Theme.textStyle.bodySmMedium
        )
        Spacer(Modifier.height(32.dp))
    }
}


@Preview
@Composable
private fun LoginScreenPreview() {
    CineVerseTheme {
        LoginScreenContainer(
            username = "",
            password = "",
            onUsernameChanged = { },
            onPasswordChanged = { },
            onLoginClicked = { },
            onLoginAsGuestClicked = { },
            onShowResetBottomSheet = { },
            onShowSignUpBottomSheet = { },
        )
    }
}