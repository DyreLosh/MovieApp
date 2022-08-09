package ru.dyrelosh.movieapp.data.network

import retrofit2.Response
import retrofit2.http.GET
import ru.dyrelosh.movieapp.data.models.Movie

interface ApiInterface {
    @GET("/shows")
    suspend fun getAllMovies() : Response<List<Movie>>
}