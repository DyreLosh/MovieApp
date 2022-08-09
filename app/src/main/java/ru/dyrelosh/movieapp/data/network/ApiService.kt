package ru.dyrelosh.movieapp.data.network

import javax.inject.Inject

class ApiService @Inject constructor(private val apiInterface: ApiInterface){
    suspend fun getAllMovies() = apiInterface.getAllMovies()
}