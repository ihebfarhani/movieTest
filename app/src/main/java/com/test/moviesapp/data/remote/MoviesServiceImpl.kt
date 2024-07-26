package com.test.moviesapp.data.remote

import com.test.moviesapp.data.network.BaseDataSource
import com.test.moviesapp.domain.MoviesDetailResponse
import com.test.moviesapp.domain.PopularsMovieResponse
import javax.inject.Inject

interface IMoviesService {

    suspend fun getPopularMovies(page: Int): PopularsMovieResponse

    suspend fun getMovieDetail(id: String): MoviesDetailResponse
}


class MoviesServiceImpl @Inject constructor(
    private val moviesService: MoviesService
) : BaseDataSource(), IMoviesService {

    override suspend fun getPopularMovies(page: Int) = getResult(
        call = { moviesService.getPopularMovies(page = page) },
        forceError = false
    )

    override suspend fun getMovieDetail(id: String) = getResult(
        call = { moviesService.getMovieDetail(id = id) },
        forceError = false
    )
}