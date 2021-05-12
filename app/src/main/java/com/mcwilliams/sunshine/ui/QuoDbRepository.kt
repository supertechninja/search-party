package com.mcwilliams.sunshine.ui

import android.content.Context
import android.content.SharedPreferences
import com.mcwilliams.sunshine.R
import com.mcwilliams.sunshine.network.QuoDbApi
import javax.inject.Inject

class QuoDbRepository @Inject constructor(
    private val quoDbApi: QuoDbApi
) {

    suspend fun getMovies(search: String): String {
        val movies = quoDbApi.getMoviesByTerm("the price is wrong")
        return movies
    }
}