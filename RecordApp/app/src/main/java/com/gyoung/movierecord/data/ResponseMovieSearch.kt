package com.gyoung.movierecord.data

data class ResponseMovieSearch(
    val page: Int,
    val results: ArrayList<MovieSearch>,
    val total_pages: Int,
    val total_results: Int
)
data class MovieSearch(
//    val backdrop_path: String,
//    val genre_ids: List<Int>,
//    val id: Int,
//    val original_title: String,
//    val overview: String,
//    val popularity: Double,
    val poster_path: String?,
    val release_date: String?,
    val title: String?
//    val vote_average: Double
)