package com.mcwilliams.searchparty.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mcwilliams.searchparty.model.QuoDbResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuoDbViewModel @Inject constructor(
    private val quoDbRepository: QuoDbRepository
) : ViewModel() {

    var _movies = MutableLiveData<QuoDbResponse>()
    var movies: LiveData<QuoDbResponse> = _movies

    fun search(text: String) {
        viewModelScope.launch {
            _movies.postValue(quoDbRepository.getMovies(text))
        }
    }

}