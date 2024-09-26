package com.vijay.randomcity.navigation

sealed class Screen(val route: String) {
    object SplashScreen : Screen("splash_screen")
    object MainScreen : Screen("main_screen")
    object DetailsScreen : Screen("details_screen/{city}/{color}") {
        fun createRoute(city: String, color: String) = "details_screen/$city/$color"
    }
}