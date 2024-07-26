package com.test.moviesapp.usecases

import androidx.paging.PagingData
import com.test.moviesapp.data.repository.IMoviesRepository
import com.test.moviesapp.domain.MovieDomain
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(
    private val iMoviesRepository: IMoviesRepository
) {
    operator suspend fun invoke(): Flow<PagingData<MovieDomain>> {
        return iMoviesRepository.getPopularMovies()
    }
}

sealed class PopularMoviesResult {
    data class Success(val pagingData: Flow<PagingData<MovieDomain>>) : PopularMoviesResult()
    data class ErrorGeneral(val error: String) : PopularMoviesResult()
    data class Loading(val isLoading: Boolean) : PopularMoviesResult()
}
