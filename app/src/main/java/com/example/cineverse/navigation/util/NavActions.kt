package com.example.cineverse.navigation.util

import androidx.navigation.NavHostController
import com.example.cineverse.navigation.ExploreRoute
import com.example.cineverse.navigation.HomeRoute
import com.example.cineverse.navigation.MatchRoute
import com.example.cineverse.navigation.ProfileRoute


fun NavHostController.navigateToHomeAsBottom() {
    navigate(HomeRoute) {
        popUpTo(HomeRoute) { inclusive = false; saveState = true }
        launchSingleTop = true
        restoreState = true
    }
}

fun NavHostController.navigateToExplore() {
    navigate(ExploreRoute) {
        popUpTo(HomeRoute) { inclusive = false; saveState = true }
        launchSingleTop = true
        restoreState = true
    }
}

fun NavHostController.navigateToMatch() {
    navigate(MatchRoute) {
        popUpTo(HomeRoute) { inclusive = false; saveState = true }
        launchSingleTop = true
        restoreState = true
    }
}

fun NavHostController.navigateToProfile() {
    navigate(ProfileRoute) {
        popUpTo(HomeRoute) { inclusive = false; saveState = true }
        launchSingleTop = true
        restoreState = true
    }
}
