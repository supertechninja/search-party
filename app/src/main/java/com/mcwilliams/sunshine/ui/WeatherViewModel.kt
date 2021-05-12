package com.mcwilliams.sunshine.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuoDbViewModel @Inject constructor(
    private val quoDbRepository: QuoDbRepository
) : ViewModel() {

    var _movies = MutableLiveData<String>()
    var movies: LiveData<String> = _weatherData

    fun search(text: String) {
        viewModelScope.launch {
            movies.postValue(quoDbRepository.getMovies(text))
        }
    }

}