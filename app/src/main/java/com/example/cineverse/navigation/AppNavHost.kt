package com.example.cineverse.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.CineVerseViewModel
import com.example.cineverse.presentation.screens.homeScreen.HomeScreen
import com.example.cineverse.presentation.screens.loginScreen.LoginScreen
import com.example.cineverse.presentation.screens.loginScreen.LoginViewModel
import com.example.cineverse.presentation.screens.onBoardingScreen.OnBoardingScreen


@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    loginViewModel: LoginViewModel,
    cineVerseViewModel: CineVerseViewModel,
) {
    val isOnBoardingCompleted  by cineVerseViewModel.isOnBoardingCompleted.collectAsStateWithLifecycle()
    val authUiState by loginViewModel.authUiState.collectAsStateWithLifecycle()
    val isLoggedIn = authUiState.isLoggedIn


    val startDestination = when{
        !isOnBoardingCompleted -> OnBoardingRoute
        !isLoggedIn -> LoginRoute
        else -> HomeRoute
    }
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {

        composable<OnBoardingRoute> {
            OnBoardingScreen(
                onGetStartedClicked = {
                    cineVerseViewModel.setOnBoardingCompleted()
                    navController.navigate(LoginRoute)
                }
            )
        }

        composable<LoginRoute> {
            LoginScreen(
                loginViewModel = loginViewModel,
                navController = navController
            )
        }

        composable<HomeRoute> {
            HomeScreen()
        }
    }
}