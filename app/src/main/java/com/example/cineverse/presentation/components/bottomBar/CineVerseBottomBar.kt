package com.example.cineverse.presentation.components.bottomBar

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.cineverse.R
import com.example.cineverse.navigation.ExploreRoute
import com.example.cineverse.navigation.HomeRoute
import com.example.cineverse.navigation.MatchRoute
import com.example.cineverse.navigation.ProfileRoute
import com.example.cineverse.presentation.designSystem.theme.CineVerseTheme
import com.example.cineverse.presentation.designSystem.theme.Theme
import com.example.cineverse.presentation.screens.matchScreen.MatchScreen


@SuppressLint("RestrictedApi")
@Composable
fun CineVerseBottomBar(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Theme.colors.backgroundCard)
            .padding(vertical = 12.dp, horizontal = 28.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        BottomNavItem(
            selectedIcon = R.drawable.due_tone_home,
            unSelectedIcon = R.drawable.outline_home,
            label = stringResource(id = R.string.home),
            isSelected = currentRoute?.hasRoute(HomeRoute::class) == true,
            onClick = {
                navController.navigate(HomeRoute) {
                    launchSingleTop = true   // to avoid duplicated nav
                }
            }
        )
        BottomNavItem(
            selectedIcon = R.drawable.due_tone_search,
            unSelectedIcon = R.drawable.outline_search,
            label = stringResource(id = R.string.explore),
            isSelected = currentRoute?.hasRoute(ExploreRoute::class) == true,
            onClick = {
                navController.navigate(ExploreRoute) {
                    launchSingleTop = true   // to avoid duplicated nav
                }
            }
        )
        BottomNavItem(
            unSelectedIcon = R.drawable.outline_magic_stick,
            selectedIcon = R.drawable.due_tone_magic_stick,
            label = stringResource(id = R.string.match),
            isSelected = currentRoute?.hasRoute(MatchScreen()::class) == true,
            onClick = {
                navController.navigate(MatchRoute) {
                    launchSingleTop = true   // to avoid duplicated nav
                }
            }
        )
        BottomNavItem(
            unSelectedIcon = R.drawable.outline_user_square,
            selectedIcon = R.drawable.due_tone_user_square,
            label = stringResource(id = R.string.me),
            isSelected = currentRoute?.hasRoute(ProfileRoute::class) == true,
            onClick = {
                navController.navigate(ProfileRoute) {
                    launchSingleTop = true   // to avoid duplicated nav
                }
            }
        )
    }
}

@Preview
@Composable
private fun BottomBarPreview() {
    CineVerseTheme {
        val navController = rememberNavController()
        CineVerseBottomBar(
            navController = navController
        )
    }
}