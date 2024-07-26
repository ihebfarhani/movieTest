package com.test.moviesapp.data.remote

import com.test.moviesapp.BuildConfig.API_KEY
import com.test.moviesapp.domain.MoviesDetailResponse
import com.test.moviesapp.domain.PopularsMovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesService {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("page") page: Int,
        @Query("api_key") apiKey: String = API_KEY
    ): Response<PopularsMovieResponse>

    @GET("movie/{id}")
    suspend fun getMovieDetail(
        @Path("id") id: String,
        @Query("api_key") apiKey: String = API_KEY,
    ): Response<MoviesDetailResponse>


}