package com.example.cineverse.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cineverse.presentation.homeScreen.HomeScreen
import com.example.cineverse.presentation.loginScreen.LoginScreen
import com.example.cineverse.presentation.loginScreen.LoginViewModel

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    loginViewModel: LoginViewModel
) {

    NavHost(
        navController = navController,
        startDestination = LoginScreen,
        modifier = modifier
    ) {
        composable<OnBoardingScreen> {

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