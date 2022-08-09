package ru.dyrelosh.movieapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.dyrelosh.movieapp.screen.viewmodel.MainViewModel
import ru.dyrelosh.movieapp.screen.DetailScreen
import ru.dyrelosh.movieapp.screen.MainScreen
import ru.dyrelosh.movieapp.screen.SplashScreen

@Composable
fun SetupNavHost(navController: NavController, viewModel: MainViewModel) {

    NavHost(
        navController = navController as NavHostController,
        startDestination = Screen.SplashScreen.route
    ) {
        composable(Screen.SplashScreen.route) {
            SplashScreen(navController = navController, viewModel)
        }
        composable(Screen.MainScreen.route) {
            MainScreen(navController = navController, viewModel)
        }
        composable(Screen.DetailScreen.route + "/{Id}") { backStackEntry ->
            DetailScreen(
                navController = navController,
                viewModel,
                backStackEntry.arguments?.getString("Id") ?: "1"
            )

        }
    }
}