package ru.dyrelosh.movieapp.screen.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.dyrelosh.movieapp.data.models.Movie
import ru.dyrelosh.movieapp.data.network.ApiService
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val apiService: ApiService) : ViewModel() {

    private val _allMovies = MutableLiveData<List<Movie>>()
    val allMovies: LiveData<List<Movie>>
        get() = _allMovies

    fun getAllMovies() {
        viewModelScope.launch {
            apiService.getAllMovies().let {
                if (it.isSuccessful) {
                    _allMovies.postValue(it.body())
                } else {
                    Log.d("checkData", "Failed to load movie ${it.errorBody()}")
                }
            }
        }
    }
}