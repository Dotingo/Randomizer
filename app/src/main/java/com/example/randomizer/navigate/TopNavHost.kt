package com.example.randomizer.navigate

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.randomizer.SettingsViewModel
import com.example.randomizer.data.type.ScreenRouteType
import com.example.randomizer.screens.SettingsScreen
import com.example.randomizer.ui.Drawer

/**
 * [NavHost] for top level screens.
 *
 * @param modifier the [Modifier] to apply on container of this nav host.
 * @param navController the [NavHostController] for this nav host. Default is
 * [rememberNavController].
 * @param startDestination string route for the start screen of this nav host. Default is
 * [ScreenRouteType.Main.route].
 */
@Composable
fun TopNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = ScreenRouteType.Main.route,
    viewModel: SettingsViewModel = hiltViewModel()
) = NavHost(
    modifier = modifier,
    navController = navController,
    route = "top_level_nav_host)",
    startDestination = startDestination,
    enterTransition = {
        slideIntoContainer(
            AnimatedContentTransitionScope.SlideDirection.Left,
            animationSpec = tween(200)
        )
    },
    exitTransition = {
        slideOutOfContainer(
            AnimatedContentTransitionScope.SlideDirection.Left,
            animationSpec = tween(200)
        )
    },
    popEnterTransition = {
        slideIntoContainer(
            AnimatedContentTransitionScope.SlideDirection.Right,
            animationSpec = tween(200)
        )
    },
    popExitTransition = {
        slideOutOfContainer(
            AnimatedContentTransitionScope.SlideDirection.Right,
            animationSpec = tween(200)
        )
    }
) {
    composable(route = ScreenRouteType.Main.route) {
        Drawer(navigateToSettingsScreen = {
            navController.navigate(route = ScreenRouteType.Settings.route) {
                launchSingleTop = true
            }
        })
    }

    composable(route = ScreenRouteType.Settings.route) {
        val context = LocalContext.current
        SettingsScreen(appTheme = viewModel.appTheme.collectAsStateWithLifecycle().value, onAppThemeChanged = {
            viewModel.saveTheme(context, it)
        }, onBack = {
            if (navController.currentBackStackEntry?.lifecycle?.currentState
                == Lifecycle.State.RESUMED
            ) {
                navController.popBackStack()
            }
        })




    }
}