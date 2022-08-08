package ru.dyrelosh.movieapp.navigation

sealed class Screen(val route: String) {
    object SplashScreen : Screen("splash_screen")
    object MainScreen : Screen("main_screen")
    object DetailScreen : Screen("detail_screen")
}