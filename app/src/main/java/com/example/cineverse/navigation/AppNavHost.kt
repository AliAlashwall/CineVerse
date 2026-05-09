package com.example.cineverse.navigation

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
    loginViewModel: LoginViewModel,
    homeViewModel: HomeViewModel,
    cineVerseViewModel: CineVerseViewModel,
    profileViewModel: ProfileViewModel
) {
    val isOnBoardingCompleted by cineVerseViewModel.isOnBoardingCompleted.collectAsStateWithLifecycle()
    val authUiState by loginViewModel.authUiState.collectAsStateWithLifecycle()
    val isLoggedIn = authUiState.isLoggedIn


    val startDestination = when {
        isOnBoardingCompleted == false -> OnBoardingRoute
        isLoggedIn == false -> LoginRoute
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

            HomeScreen(navController = navController, homeViewModel = homeViewModel)
        }

        composable<ExploreRoute> {
            ExploreScreen()
        }
        composable<MatchRoute> {
            MatchScreen()
        }
        composable<ProfileRoute> {
            ProfileScreen(profileViewModel = profileViewModel)
        }
        composable<EmptyRoute> {
            EmptyScreen()
        }

        composable<MovieDetailsRoute> { backStackEntry ->
            // To ensure  it is only created when the navigation destination is active and the required arguments are available.
            val movieDetailsViewModel: MovieDetailsViewModel = hiltViewModel()

            val arg = backStackEntry.toRoute<MovieDetailsRoute>()
            MovieDetailsScreen(
                movieDetailsViewModel = movieDetailsViewModel,
                movieId = arg.movieId,
                navController = navController
            )
        }

    }
}
