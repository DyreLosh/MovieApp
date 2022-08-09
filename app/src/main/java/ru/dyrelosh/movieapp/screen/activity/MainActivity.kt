package ru.dyrelosh.movieapp.screen.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.dyrelosh.movieapp.screen.viewmodel.MainViewModel
import ru.dyrelosh.movieapp.navigation.SetupNavHost
import ru.dyrelosh.movieapp.ui.theme.MovieAppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieAppTheme {
                val viewModel = hiltViewModel<MainViewModel>()
                val navController = rememberNavController()
                SetupNavHost(navController = navController, viewModel)
            }
        }
    }
}
