package com.example.cineverse.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cineverse.presentation.screens.homeScreen.HomeScreen
import com.example.cineverse.presentation.screens.loginScreen.LoginScreen
import com.example.cineverse.presentation.screens.loginScreen.LoginViewModel
import com.example.cineverse.presentation.screens.onBoardingScreen.OnBoardingScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    loginViewModel: LoginViewModel
) {

    NavHost(
        navController = navController,
        startDestination = OnBoardingScreen,
        modifier = modifier
    ) {
        composable<OnBoardingScreen> {
            OnBoardingScreen(
                onGetStartedClicked = {
                    navController.navigate(LoginScreen)
                }
            )
        }

        composable<LoginScreen> {
            LoginScreen(
                loginViewModel = loginViewModel,
                navController = navController
            )
        }

        composable<HomeScreen> {
            HomeScreen()
        }

    }
}