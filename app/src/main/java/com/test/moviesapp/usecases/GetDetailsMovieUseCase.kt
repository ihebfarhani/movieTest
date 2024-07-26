package com.test.moviesapp.usecases

import com.test.moviesapp.data.repository.IMoviesRepository
import com.test.moviesapp.domain.MovieDetailDomain
import com.test.moviesapp.domain.toDomainModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetDetailsMovieUseCase @Inject constructor(
    private val iMoviesRepository: IMoviesRepository
) {
    suspend operator fun invoke(
        id: String
    ) = iMoviesRepository.getMovieDetail(id).map {
        it.toDomainModel()
    }
}

sealed class GetDetailsMovieResult {
    data class Loading(val isLoading: Boolean) : GetDetailsMovieResult()
    data class Success(val data: MovieDetailDomain) : GetDetailsMovieResult()
    data class Error(val message: String) : GetDetailsMovieResult()
    data class InternetError(val message: String) : GetDetailsMovieResult()
}

