package com.mcwilliams.searchparty.ui

import com.mcwilliams.searchparty.model.QuoDbResponse
import com.mcwilliams.searchparty.network.QuoDbApi
import javax.inject.Inject

class QuoDbRepository @Inject constructor(
    private val quoDbApi: QuoDbApi
) {

    suspend fun getMovies(search: String): QuoDbResponse {
        return quoDbApi.getMoviesByTerm(searchTerm = search)
    }
}