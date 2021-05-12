package com.mcwilliams.searchparty.network

import com.mcwilliams.searchparty.model.QuoDbResponse
import retrofit2.http.GET
import retrofit2.http.Path


interface QuoDbApi {
    @GET("/search/{searchTerm}")
    suspend fun getMoviesByTerm(@Path("searchTerm") searchTerm: String): QuoDbResponse
}