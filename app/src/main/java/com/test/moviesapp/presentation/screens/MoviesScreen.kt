package com.test.moviesapp.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.test.moviesapp.domain.MovieDomain
import com.test.moviesapp.presentation.composables.CustomErrorScreenSomethingHappens
import com.test.moviesapp.presentation.composables.HorizontalMovieItem
import com.test.moviesapp.presentation.composables.LoadingScreen
import com.test.moviesapp.usecases.PopularMoviesResult
import kotlinx.coroutines.flow.Flow


@Composable
fun MoviesScreen(
    moviesList: PopularMoviesResult,
    onClickNavigateToDetails: (Int) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {

        Spacer(modifier = Modifier.height(16.dp))

        HeaderMoviesScreen(
            onClickNavigateToDetails = onClickNavigateToDetails,
            popularMoviesState = moviesList
        )


    }
}

@Composable
fun HeaderMoviesScreen(
    onClickNavigateToDetails: (Int) -> Unit,
    popularMoviesState: PopularMoviesResult
) {
    var isErrorGeneral by rememberSaveable { mutableStateOf(false) }
    var isSuccess by rememberSaveable { mutableStateOf(false) }
    var isLoading by rememberSaveable { mutableStateOf(false) }
    var popularMoviesList by remember { mutableStateOf<Flow<PagingData<MovieDomain>>?>(null) }

    LaunchedEffect(key1 = popularMoviesState) {
        when (popularMoviesState) {

            is PopularMoviesResult.ErrorGeneral -> {
                isLoading = false
                isErrorGeneral = true
            }

            is PopularMoviesResult.Loading -> {
                isLoading = popularMoviesState.isLoading
                isErrorGeneral = false
            }

            is PopularMoviesResult.Success -> {
                isLoading = false
                isErrorGeneral = false
                isSuccess = true
                popularMoviesList = popularMoviesState.pagingData
            }
        }
    }

    val lazyPagingItems = popularMoviesList?.collectAsLazyPagingItems()

    when {
        isLoading -> {
            LoadingScreen()
        }

        isErrorGeneral -> {
            CustomErrorScreenSomethingHappens(
                modifier = Modifier.padding(bottom = 180.dp),
            )
        }

        isSuccess -> {
            if (lazyPagingItems != null)
                LazyColumn {
                    items(
                        count = lazyPagingItems.itemCount,
                        key = lazyPagingItems.itemKey { it.id },
                        contentType = lazyPagingItems.itemContentType { "contentType" }) { index ->
                        val item = lazyPagingItems[index]!!
                        HorizontalMovieItem(
                            title = item.title,
                            description = item.overview,
                            imageUrl = item.poster_path,
                            realeaseDate = item.release_date ?: "",
                            onClick = { onClickNavigateToDetails(item.id) })
                    }
                    lazyPagingItems.apply {
                        when (loadState.append) {
                            is LoadState.Loading -> {
                                item {
                                    LoadingScreen()
                                }
                            }

                            is LoadState.Error -> {
                                item {
                                    CustomErrorScreenSomethingHappens(
                                        modifier = Modifier.padding(bottom = 180.dp),
                                    )
                                }
                            }

                            is LoadState.NotLoading -> {
                                // Later
                            }
                        }
                    }
                }
        }
    }


}
