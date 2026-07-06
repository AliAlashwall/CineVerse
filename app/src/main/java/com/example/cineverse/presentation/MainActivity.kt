package com.example.cineverse.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.CineVerseViewModel
import com.example.cineverse.navigation.AppNavHost
import com.example.cineverse.navigation.ExploreRoute
import com.example.cineverse.navigation.HomeRoute
import com.example.cineverse.navigation.MatchRoute
import com.example.cineverse.navigation.ProfileRoute
import com.example.cineverse.navigation.NavActions
import com.example.cineverse.presentation.components.bottomBar.CineVerseBottomBar
import com.example.cineverse.presentation.designSystem.theme.CineVerseTheme
import com.example.cineverse.presentation.designSystem.theme.Theme
import dagger.hilt.android.AndroidEntryPoint
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavDestination.Companion.hasRoute
import com.example.cineverse.presentation.designSystem.theme.rememberThemeState

@AndroidEntryPoint
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
class MainActivity : ComponentActivity() {

    private val cineVerseViewModel: CineVerseViewModel by viewModels()

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()

        super.onCreate(savedInstanceState)

        // Keep Splash Screen visible until DataStore is ready
        splashScreen.setKeepOnScreenCondition {
            cineVerseViewModel.isOnBoardingCompleted.value == null ||
            !cineVerseViewModel.isThemeLoaded.value
        }

        enableEdgeToEdge()
        setContent {
            val isThemeLoaded by cineVerseViewModel.isThemeLoaded.collectAsStateWithLifecycle()
            val isOnBoardingCompleted by cineVerseViewModel.isOnBoardingCompleted.collectAsStateWithLifecycle()

            // GATE: Don't render any UI until we know the correct theme and onboarding state.
            // This prevents the flickering because the first frame shown will have the final colors.
            if (!isThemeLoaded || isOnBoardingCompleted == null) return@setContent

            val navController = rememberNavController()
            val navActions = remember(navController) { NavActions(navController) }

            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val bottomBarScreens = listOf(HomeRoute, ExploreRoute, MatchRoute, ProfileRoute)
            val showBottomBar = bottomBarScreens.any { screen ->
                navBackStackEntry?.destination?.hasRoute(screen::class) == true
            }
            
            val isDarkStored by cineVerseViewModel.isDarkTheme.collectAsStateWithLifecycle()
            val isDark = isDarkStored ?: isSystemInDarkTheme()

            CineVerseTheme(state = rememberThemeState(isDark = isDark)) {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = Theme.colors.backgroundScreen,
                    bottomBar = {
                        if (showBottomBar) {
                            CineVerseBottomBar(navController = navController, navActions = navActions)
                        }
                    }
                ) { innerPadding ->
                    AppNavHost(
                        navController = navController,
                        navActions = navActions,
                        modifier = Modifier.padding(innerPadding),
                        cineVerseViewModel = cineVerseViewModel
                    )
                }
            }
        }
    }
}
