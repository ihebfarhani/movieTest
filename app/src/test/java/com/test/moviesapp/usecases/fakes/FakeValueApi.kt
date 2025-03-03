package com.test.moviesapp.usecases.fakes

import com.test.moviesapp.domain.MovieEntity
import com.test.moviesapp.domain.PopularsMovieResponse

object FakeValueApi {

    fun popularResponseFake() = PopularsMovieResponse(
        page = 1,
        results = listMovieEntityFake(),
        total_pages = 1,
        total_results = 1
    )

    private fun listMovieEntityFake() = listOf(
        MovieEntity(
            id = 1,
            overview = "",
            poster_path = "",
            release_date = "",
            title = "",
            vote_average = 1.0f
        )
    )
}