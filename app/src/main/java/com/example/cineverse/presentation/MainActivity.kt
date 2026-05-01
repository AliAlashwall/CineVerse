package com.example.cineverse.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.CineVerseViewModel
import com.example.cineverse.navigation.AppNavHost
import com.example.cineverse.navigation.ExploreRoute
import com.example.cineverse.navigation.HomeRoute
import com.example.cineverse.navigation.MatchRoute
import com.example.cineverse.navigation.ProfileRoute
import com.example.cineverse.presentation.components.bottomBar.CineVerseBottomBar
import com.example.cineverse.presentation.designSystem.theme.CineVerseTheme
import com.example.cineverse.presentation.screens.loginScreen.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
class MainActivity : ComponentActivity() {
    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val loginViewModel: LoginViewModel = hiltViewModel()
            val cineVerseViewModel: CineVerseViewModel = hiltViewModel()

            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val bottomBarScreens = listOf(HomeRoute, ExploreRoute, MatchRoute, ProfileRoute)
            val showBottomBar = bottomBarScreens.any { screen ->
                navBackStackEntry?.destination?.hasRoute(screen::class) == true
            }

            CineVerseTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar =
                        {
                            if (showBottomBar) {
                                CineVerseBottomBar(navController = navController)
                            }
                        }
                ) { innerPadding ->
                    AppNavHost(
                        navController = navController,
                        modifier = Modifier.padding(innerPadding),
                        loginViewModel = loginViewModel,
                        cineVerseViewModel = cineVerseViewModel
                    )
                }
            }
        }
    }
}