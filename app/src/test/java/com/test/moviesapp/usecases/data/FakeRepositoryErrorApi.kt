package com.test.moviesapp.usecases.data

import androidx.paging.PagingData
import com.test.moviesapp.data.repository.IMoviesRepository
import com.test.moviesapp.domain.MovieDomain
import com.test.moviesapp.domain.MoviesDetailResponse
import com.test.moviesapp.domain.PopularsMovieResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.HttpException

class FakeRepositoryErrorApi : IMoviesRepository {
    override suspend fun getPopularMovies(): Flow<PagingData<MovieDomain>> {
        return flow {
            throw HttpException(
                retrofit2.Response.error<PopularsMovieResponse>(
                    500,
                    "Error".toResponseBody("application/json".toMediaTypeOrNull())
                )
            )
        }
    }

    override suspend fun getMovieDetail(
        id: String
    ): Flow<MoviesDetailResponse> {
        return flow {
            throw HttpException(
                retrofit2.Response.error<MoviesDetailResponse>(
                    500,
                    "Error".toResponseBody("application/json".toMediaTypeOrNull())
                )
            )
        }
    }

}