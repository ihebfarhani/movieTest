package com.test.moviesapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.test.moviesapp.presentation.screens.DetailsMovieScreen
import com.test.moviesapp.presentation.screens.MoviesScreen
import com.test.moviesapp.presentation.viewmodels.DetailsMovieViewModel
import com.test.moviesapp.presentation.viewmodels.MoviesViewModel
import timber.log.Timber

@Composable
fun RootNavigationGraph(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        route = Graph.HOME,
        startDestination = HomeScreen.MoviesHomeScreen.route,
    ) {

        composable(HomeScreen.MoviesHomeScreen.route) {
            val moviesViewModel = hiltViewModel<MoviesViewModel>()
            val moviesState by moviesViewModel.movies.collectAsStateWithLifecycle()
            MoviesScreen(
                moviesList = moviesState,
                onClickNavigateToDetails = { movieID ->
                    Timber.d("movieId saved: $movieID")
                    navController.navigate(route = Graph.DETAILS + "/$movieID")
                }
            )
        }

        detailsNavGraph(navController = navController)
    }
}


fun NavGraphBuilder.detailsNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.DETAILS + "/{movieId}",
        startDestination = DetailsScreen.Information.route
    ) {

        composable(DetailsScreen.Information.route) {
            val movieId = it.arguments?.getString("movieId") ?: ""
            val detailsMovieViewModel = hiltViewModel<DetailsMovieViewModel>()
            val stateMovieDetail by detailsMovieViewModel.detailsMovie.collectAsStateWithLifecycle()
            Timber.d("movieId retrieved: ${movieId}")
            DetailsMovieScreen(
                navController = navController,
                stateMovieDetail = stateMovieDetail
            )
        }
    }


}


sealed class DetailsScreen(val route: String) {
    object Information : DetailsScreen("information_screen")
}

sealed class HomeScreen(val route: String) {
    object MoviesHomeScreen : HomeScreen("movies_screen")
}


object Graph {
    const val HOME = "home_graph"
    const val DETAILS = "details_graph"
}