package com.test.moviesapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.test.moviesapp.data.remote.IMoviesService
import com.test.moviesapp.domain.MovieDomain
import com.test.moviesapp.domain.MoviesDetailResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface IMoviesRepository {

    suspend fun getPopularMovies(): Flow<PagingData<MovieDomain>>

    suspend fun getMovieDetail(id: String): Flow<MoviesDetailResponse>
}

class MoviesRepository @Inject constructor(
    private val remote: IMoviesService
) : IMoviesRepository {

    override suspend fun getPopularMovies(): Flow<PagingData<MovieDomain>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { PopularMoviesSource(remote) }
        ).flow
    }

    override suspend fun getMovieDetail(id: String): Flow<MoviesDetailResponse> = flow {
        emit(remote.getMovieDetail(id))
    }
}

