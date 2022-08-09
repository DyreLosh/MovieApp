package ru.dyrelosh.movieapp.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import ru.dyrelosh.movieapp.screen.viewmodel.MainViewModel
import ru.dyrelosh.movieapp.data.models.Movie
import ru.dyrelosh.movieapp.navigation.Screen

@Composable
fun MainScreen(navController: NavController, viewModel: MainViewModel) {

    rememberSystemUiController().setStatusBarColor(
        color = Color.Black,
        darkIcons = false
    )
    val allMovies = viewModel.allMovies.observeAsState(listOf()).value
    allMovies.forEach {
        Log.d("checkData", "ID ${it.id}, name: ${it.name}")
    }

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.Black,
                contentColor = Color.White,
                title = { Text(text = "Movie List") }
            )
        }) {
        LazyColumn(modifier = Modifier.padding(top = 8.dp)) {
            items(allMovies) { item ->
                MovieCard(item = item, navController)
            }
        }
    }
}

@OptIn(ExperimentalCoilApi::class, ExperimentalMaterialApi::class)
@Composable
fun MovieCard(item: Movie, navController: NavController) {
    Card(
        elevation = 4.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp),
        shape = RoundedCornerShape(20.dp),
        onClick = { navController.navigate(Screen.DetailScreen.route + "/${item.id}") }

    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Image(
                painter = rememberImagePainter(item.image.medium),
                contentDescription = "sadfas",
                modifier = Modifier.size(128.dp)
            )
            Column() {
                Text(
                    text = item.name,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Row(modifier = Modifier.padding(top = 8.dp)) {
                    Text(text = "Rating: ", fontWeight = FontWeight.Bold)
                    Text(text = item.rating.average.toString())
                }
                Row() {
                    Text(text = "Genre: ", fontWeight = FontWeight.Bold)
                    item.genres.take(2).forEach { Text(text = " $it ") }
                }
                Row() {
                    Text(text = "Premier year: ", fontWeight = FontWeight.Bold)
                    Text(text = item.premiered)
                }
            }
        }
    }
}