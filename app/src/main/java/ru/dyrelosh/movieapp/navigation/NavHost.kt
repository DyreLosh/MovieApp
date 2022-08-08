package ru.dyrelosh.movieapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.dyrelosh.movieapp.screen.DetailScreen
import ru.dyrelosh.movieapp.screen.MainScreen
import ru.dyrelosh.movieapp.screen.SplashScreen

@Composable
fun SetupNavHost(navController: NavController) {

    NavHost(
        navController = navController as NavHostController,
        startDestination = Screen.SplashScreen.route
    ) {
        composable(Screen.SplashScreen.route) {
            SplashScreen(navController = navController)
        }
        composable(Screen.MainScreen.route) {
            MainScreen(navController = navController)
        }
        composable(Screen.DetailScreen.route) {
            DetailScreen(navController = navController)
        }
    }
}