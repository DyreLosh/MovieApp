package ru.dyrelosh.movieapp.screen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.delay
import ru.dyrelosh.movieapp.screen.viewmodel.MainViewModel
import ru.dyrelosh.movieapp.navigation.Screen

@Composable
fun SplashScreen(navController: NavController, viewModel: MainViewModel) {

    rememberSystemUiController().setSystemBarsColor(color = Color.White, darkIcons = true)
    var startAnimation by remember {
        mutableStateOf(false)
    }
    val alphaAnimation = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(3000)
    )

    LaunchedEffect(key1 = true) {
        startAnimation = true
        viewModel.getAllMovies()
        delay(4000)
        navController.navigate(Screen.MainScreen.route)
    }
    Splash(alphaAnimation.value)
}

@Composable
fun Splash(alpha: Float) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Icon(
            imageVector = Icons.Default.PlayArrow,
            contentDescription = "",
            tint = Color.Black,
            modifier = Modifier
                .size(120.dp)
                .alpha(alpha)
        )
    }
}