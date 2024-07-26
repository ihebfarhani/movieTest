package com.test.moviesapp.usecases.data

import androidx.paging.PagingData
import com.test.moviesapp.data.repository.IMoviesRepository
import com.test.moviesapp.domain.MovieDomain
import com.test.moviesapp.domain.MoviesDetailResponse
import com.test.moviesapp.domain.toDomainModel
import com.test.moviesapp.usecases.fakes.FakeValueApi.popularResponseFake
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.time.Duration.Companion.seconds


class FakeRepositorySuccessApi : IMoviesRepository {
    override suspend fun getPopularMovies(): Flow<PagingData<MovieDomain>> {
        return flow {
            delay(2.seconds) // Simulate network delay
            emit(PagingData.from(popularResponseFake().results.toDomainModel()))
        }
    }

    override suspend fun getMovieDetail(
        id: String
    ): Flow<MoviesDetailResponse> {
        return flow {
            emit(
                MoviesDetailResponse(
                    adult = false,
                    backdrop_path = "",
                    belongs_to_collection = null,
                    budget = 1,
                    genres = listOf(),
                    homepage = "",
                    id = 1,
                    imdb_id = "",
                    original_language = "",
                    original_title = "",
                    overview = "",
                    popularity = 1.0,
                    poster_path = "",
                    production_companies = listOf(),
                    production_countries = listOf(),
                    release_date = "",
                    revenue = 1,
                    runtime = 1,
                    spoken_languages = listOf(),
                    status = "",
                    tagline = "",
                    title = "",
                    video = false,
                    vote_average = 1.0,
                    vote_count = 1
                )
            )
        }
    }
}