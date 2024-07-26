package com.test.moviesapp.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.test.moviesapp.data.remote.IMoviesService
import com.test.moviesapp.domain.MovieDomain
import com.test.moviesapp.domain.toDomainModel
import okio.IOException
import retrofit2.HttpException


class PopularMoviesSource(private val api: IMoviesService) :
    PagingSource<Int, MovieDomain>() {
    override fun getRefreshKey(state: PagingState<Int, MovieDomain>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieDomain> {
        return try {
            val nextPage = params.key ?: 1
            val popularMovies = api.getPopularMovies(page = nextPage)
            LoadResult.Page(
                data = popularMovies.results.toDomainModel(),
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (popularMovies.results.isEmpty()) null else popularMovies.page + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}