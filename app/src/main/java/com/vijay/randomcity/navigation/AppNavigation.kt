package com.vijay.randomcity.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.vijay.randomcity.ui.common.CityViewModel
import com.vijay.randomcity.ui.screen.DetailsScreen
import com.vijay.randomcity.ui.screen.MainScreen
import com.vijay.randomcity.ui.screen.SplashScreen

@Composable
fun AppNavigation(navController: NavHostController, viewModel: CityViewModel) {
    NavHost(navController = navController, startDestination = Screen.SplashScreen.route) {
        composable(Screen.SplashScreen.route) {
            SplashScreen(navController = navController, viewModel = viewModel)
        }
        composable(Screen.MainScreen.route) {
            MainScreen(navController = navController, viewModel = viewModel)
        }
        composable(
            Screen.DetailsScreen.route,
            arguments = listOf(
                navArgument("city") { type = NavType.StringType },
                navArgument("color") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val city = backStackEntry.arguments?.getString("city") ?: ""
            val color = backStackEntry.arguments?.getString("color") ?: ""
            DetailsScreen(city = city, color = color)
        }
    }
}