package com.example.cineverse.navigation

import androidx.navigation.NavHostController

class NavActions(private val navController: NavHostController) {

    fun navigateToOnBoarding() {
        navController.navigate(OnBoardingRoute) {
            launchSingleTop = true
        }
    }

    fun navigateToLogin() {
        navController.navigate(LoginRoute) {
            popUpTo(OnBoardingRoute) { inclusive = true }
            launchSingleTop = true
        }
    }

    fun navigateToHome() {
        navController.navigate(HomeRoute) {
            popUpTo(LoginRoute) { inclusive = true }
            launchSingleTop = true
        }
    }

    fun navigateToProfile() {
        navController.navigate(ProfileRoute) {
            popUpTo(HomeRoute) { inclusive = false; saveState = true }
            launchSingleTop = true
            restoreState = true
        }
    }

    fun navigateToExplore() {
        navController.navigate(ExploreRoute) {
            popUpTo(HomeRoute) { inclusive = false; saveState = true }
            launchSingleTop = true
            restoreState = true
        }
    }

    fun navigateToMatch() {
        navController.navigate(MatchRoute) {
            popUpTo(HomeRoute) { inclusive = false; saveState = true }
            launchSingleTop = true
            restoreState = true
        }
    }

    fun navigateToHomeAsBottom() {
        navController.navigate(HomeRoute) {
            popUpTo(HomeRoute) { inclusive = false; saveState = true }
            launchSingleTop = true
            restoreState = true
        }
    }

    fun openMovieDetails(movieId: Int) {
        navController.navigate(MovieDetailsRoute(movieId))
    }

    fun navigateUp() {
        navController.popBackStack()
    }
}
