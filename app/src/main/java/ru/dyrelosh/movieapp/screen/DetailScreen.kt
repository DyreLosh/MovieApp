package ru.dyrelosh.movieapp.screen

import android.widget.TextView
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.UriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import ru.dyrelosh.movieapp.data.models.Movie
import ru.dyrelosh.movieapp.screen.viewmodel.MainViewModel

@OptIn(ExperimentalCoilApi::class)
@Composable
fun DetailScreen(navController: NavController, viewModel: MainViewModel, itemId: String) {
    val currentItem = viewModel.allMovies
        .observeAsState(listOf()).value
        .firstOrNull { it.id == itemId.toInt() }
    val uriHandler = LocalUriHandler.current


    Scaffold(
        topBar = {
            DetailTopBar(
                currentItem,
                navController,
                uriHandler
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 12.dp, bottom = 8.dp)
        ) {
            item {
                DetailCard(currentItem)
            }
        }
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun DetailCard(currentItem: Movie?) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        Image(
            painter = rememberImagePainter(currentItem?.image?.medium),
            contentDescription = "",
            modifier = Modifier.size(300.dp)
        )
        Row(Modifier.padding(8.dp)) {
            Text(text = "Genre: ", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            currentItem?.genres?.take(2)?.forEach {
                Text(text = " [$it] ", fontSize = 18.sp)
            }
        }
        Row(Modifier.padding(8.dp)) {
            Text(text = "Rating: ", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Text(text = currentItem?.rating?.average.toString(), fontSize = 18.sp)
        }
        HtmlText(
            html = currentItem?.summary.toString(),
            modifier = Modifier.padding(top = 18.dp)
        )
    }
}

@Composable
fun DetailTopBar(currentItem: Movie?, navController: NavController, uriHandler: UriHandler) {
    TopAppBar(
        backgroundColor = Color.Black,
        contentColor = Color.White,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Spacer(modifier = Modifier.width(10.dp))
                Icon(
                    Icons.Default.ArrowBack,
                    contentDescription = "",
                    tint = Color.White,
                    modifier = Modifier.clickable {
                        navController.popBackStack()
                    })
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = currentItem?.name.toString(),
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color.White
                )
            }
            Row() {
                Icon(
                    Icons.Default.Share,
                    contentDescription = "to site",
                    tint = Color.White,
                    modifier = Modifier.clickable {
                        uriHandler.openUri("${currentItem?.url}")
                    }
                )
                Spacer(modifier = Modifier.width(10.dp))
            }
        }
    }
}

@Composable
fun HtmlText(html: String, modifier: Modifier) {
    AndroidView(
        modifier = modifier,
        factory = { context -> TextView(context) },
        update = { it.text = HtmlCompat.fromHtml(html, HtmlCompat.FROM_HTML_MODE_COMPACT) }
    )
}

