package com.test.moviesapp.domain

import com.test.moviesapp.BuildConfig


data class PopularsMovieResponse(
    val page: Int,
    val results: List<MovieEntity>,
    val total_pages: Int,
    val total_results: Int
)

data class MovieDomain(
    val id: Int,
    val poster_path: String,
    val overview: String,
    val title: String,
    val release_date: String? = null,
)


data class MovieEntity(
    val id: Int,
    val poster_path: String,
    val overview: String,
    val title: String,
    val vote_average: Float,
    val release_date: String? = null,
)

fun List<MovieEntity>.toDomainModel(): List<MovieDomain> {
    return map {
        MovieDomain(
            id = it.id,
            poster_path = BuildConfig.IMAGE_URL + it.poster_path,
            overview = it.overview,
            title = it.title,
            release_date = it.release_date
        )
    }
}


