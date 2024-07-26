package com.test.moviesapp.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.test.moviesapp.domain.MovieDetailDomain
import com.test.moviesapp.presentation.composables.CustomErrorScreenSomethingHappens
import com.test.moviesapp.presentation.composables.LoadingScreen
import com.test.moviesapp.usecases.GetDetailsMovieResult

@Composable
fun DetailsMovieScreen(
    navController: NavController,
    stateMovieDetail: GetDetailsMovieResult
) {
    var isLoading by remember { mutableStateOf(false) }
    var isError by remember { mutableStateOf(false) }
    var isSuccess by remember { mutableStateOf(false) }

    var item by remember { mutableStateOf(MovieDetailDomain()) }


    LaunchedEffect(key1 = stateMovieDetail) {
        when (stateMovieDetail) {
            is GetDetailsMovieResult.Success -> {
                isLoading = false
                isError = false
                isSuccess = true
                item = stateMovieDetail.data
            }

            is GetDetailsMovieResult.Error -> {
                isLoading = false
                isSuccess = false
                isError = true
            }

            is GetDetailsMovieResult.Loading -> {
                isError = false
                isSuccess = false
                isLoading = stateMovieDetail.isLoading
            }

            is GetDetailsMovieResult.InternetError -> {
                isLoading = false
                isError = false
                isSuccess = false
            }
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        when {
            isLoading -> {
                LoadingScreen()
            }

            isError -> {
                CustomErrorScreenSomethingHappens()
            }

            isSuccess -> {
                DetailsMovieContent(
                    onClickBack = {
                        navController.popBackStack()
                    },
                    title = item.title ?: "",
                    description = item.overview ?: "",
                    imageBackdrop = item.backdrop_path ?: "",
                    genres = item.genres ?: listOf(),
                    releaseDate = item.release_date ?: "",
                    runtime = item.runtimeWithMinutes ?: "",
                )
            }
        }
    }
}

