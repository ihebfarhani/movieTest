package com.test.moviesapp.presentation.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.moviesapp.usecases.GetDetailsMovieResult
import com.test.moviesapp.usecases.GetDetailsMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

@HiltViewModel
class DetailsMovieViewModel @Inject constructor(
    private val getDetailsMovieUseCase: GetDetailsMovieUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _detailsMovie =
        MutableStateFlow<GetDetailsMovieResult>(GetDetailsMovieResult.Loading(false))
    val detailsMovie = _detailsMovie.stateIn(
        scope = viewModelScope,
        started = kotlinx.coroutines.flow.SharingStarted.WhileSubscribed(5000, 1),
        initialValue = GetDetailsMovieResult.Loading(false)
    )

    init {
        val idMovie = savedStateHandle.get<String>("movieId") ?: ""
        start(idMovie)
    }

    private fun start(idMovie: String) = viewModelScope.launch {
        getDetailsMovie(idMovie).join()
    }

    private fun getDetailsMovie(id: String) = viewModelScope.launch(Dispatchers.IO) {
        getDetailsMovieUseCase.invoke(
            id = id
        ).onStart {
            _detailsMovie.value = GetDetailsMovieResult.Loading(true)
            delay(1.seconds) // Just to see the loading screen
        }.onEach {
            _detailsMovie.value = GetDetailsMovieResult.Success(it)
        }.catch {
            Timber.e("DetailsMovieViewModel: ${it.message}")
            _detailsMovie.value = GetDetailsMovieResult.Error("Error, ${it.message}")
        }.launchIn(viewModelScope)
    }


}