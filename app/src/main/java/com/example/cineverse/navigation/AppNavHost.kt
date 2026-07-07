package com.example.cineverse.navigation

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.CineVerseViewModel
import com.example.cineverse.navigation.util.navigateToProfile
import com.example.cineverse.presentation.screens.EmptyScreen
import com.example.cineverse.presentation.screens.exploreScreen.ExploreScreen
import com.example.cineverse.presentation.screens.homeScreen.HomeScreen
import com.example.cineverse.presentation.screens.homeScreen.HomeViewModel
import com.example.cineverse.presentation.screens.loginScreen.LoginScreen
import com.example.cineverse.presentation.screens.loginScreen.LoginViewModel
import com.example.cineverse.presentation.screens.matchScreen.MatchScreen
import com.example.cineverse.presentation.screens.movieDetailsScreen.MovieDetailsScreen
import com.example.cineverse.presentation.screens.movieDetailsScreen.MovieDetailsViewModel
import com.example.cineverse.presentation.screens.onBoardingScreen.OnBoardingScreen
import com.example.cineverse.presentation.screens.profileScreen.ProfileScreen
import com.example.cineverse.presentation.screens.profileScreen.ProfileViewModel


@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    cineVerseViewModel: CineVerseViewModel
) {
    val isOnBoardingCompleted by cineVerseViewModel.isOnBoardingCompleted.collectAsStateWithLifecycle()
    val isDarkStored by cineVerseViewModel.isDarkTheme.collectAsStateWithLifecycle()

    // Consistently fall back to system theme if no preference is stored/loaded yet
    val isDark = isDarkStored ?: isSystemInDarkTheme()


    val startDestination = when {
        isOnBoardingCompleted == false -> OnBoardingRoute
        // We don't have a LoginViewModel here; login state checked inside Login destination
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
                    navController.navigate(LoginRoute) {
                        popUpTo(OnBoardingRoute) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }

        composable<LoginRoute> { backStackEntry ->
            val loginViewModel: LoginViewModel = hiltViewModel(backStackEntry)
            LoginScreen(
                loginViewModel = loginViewModel,
                navigateToHome = {
                    navController.navigate(HomeRoute) {
                        popUpTo(LoginRoute) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }

        composable<HomeRoute> { backStackEntry ->
            val homeViewModel: HomeViewModel = hiltViewModel(backStackEntry)

            HomeScreen(
                homeViewModel = homeViewModel,
                onMovieClicked = { movie -> navController.navigate(MovieDetailsRoute(movie.id)) },
                onHeaderClicked = { navController.navigateToProfile() }
            )
        }

        composable<ExploreRoute> {
            ExploreScreen(
                onMovieClicked = { movieId -> navController.navigate(MovieDetailsRoute(movieId)) }
            )
        }
        composable<MatchRoute> {
            MatchScreen()
        }
        composable<ProfileRoute> { backStackEntry ->
            val profileViewModel: ProfileViewModel = hiltViewModel(backStackEntry)
            ProfileScreen(
                profileViewModel = profileViewModel,
                isDark = isDark,
                onSwitchClicked = { isDarkValue ->
                    cineVerseViewModel.setAppTheme(isDarkValue)
                }
            )
        }
        composable<EmptyRoute> {
            EmptyScreen()
        }

        composable<MovieDetailsRoute> { backStackEntry ->
            val movieDetailsViewModel: MovieDetailsViewModel = hiltViewModel(backStackEntry)

            val arg = backStackEntry.toRoute<MovieDetailsRoute>()
            MovieDetailsScreen(
                movieDetailsViewModel = movieDetailsViewModel,
                movieId = arg.movieId,
                openMovieDetailsById = { movieId -> navController.navigate(MovieDetailsRoute(movieId)) },
                onBackClicked = { navController.popBackStack() }
            )
        }

    }
}