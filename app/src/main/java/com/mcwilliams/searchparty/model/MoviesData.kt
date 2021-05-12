package com.mcwilliams.searchparty.model

data class QuoDbResponse(
    val docs: List<Movie>,
    val numFound: Long,
    val numPhrasesFound: Long,
    val profanitiesFound: Long
)

data class Movie(
    val title_id: String,
    val title: String,
    val year: Long,
    val image: String,
    val phrase: String,
    val time: Long
)