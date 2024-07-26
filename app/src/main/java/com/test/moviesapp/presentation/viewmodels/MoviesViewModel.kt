package com.test.moviesapp.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.test.moviesapp.usecases.GetPopularMoviesUseCase
import com.test.moviesapp.usecases.PopularMoviesResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase
) : ViewModel() {

    private val _moviesStateResult =
        MutableStateFlow<PopularMoviesResult>(PopularMoviesResult.Loading(false))

    val movies = _moviesStateResult.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000, 1),
        initialValue = PopularMoviesResult.Loading(false)
    )

    init {
        getPopularMovies()
    }


    fun getPopularMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            getPopularMoviesUseCase()
                .cachedIn(viewModelScope)
                .onStart {
                    _moviesStateResult.value = PopularMoviesResult.Loading(true)
                }
                .catch { e ->
                    _moviesStateResult.value =
                        PopularMoviesResult.ErrorGeneral(e.message ?: "General error")
                }
                .collect { pagingData ->
                    _moviesStateResult.value = PopularMoviesResult.Success(flowOf(pagingData))
                }
        }
    }
}