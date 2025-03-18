package com.piashcse.hilt_mvvm_compose_movie.ui.screens.movies.toprated

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.piashcse.hilt_mvvm_compose_movie.data.model.GenreId
import com.piashcse.hilt_mvvm_compose_movie.data.model.moviedetail.Genre
import com.piashcse.hilt_mvvm_compose_movie.ui.component.Movies

@Composable
fun TopRatedMovie(
    navController: NavController,
    genres: List<Genre>? = null,
) {
    val topRatedViewModel = hiltViewModel<TopRatedMovieViewModel>()
    Movies(
        navController = navController,
        moviesItems = topRatedViewModel.topRatedMovies.collectAsLazyPagingItems(),
        genres = genres,
        selectedName = topRatedViewModel.selectedGenre.value
    ){
        topRatedViewModel.filterData.value =  GenreId(it?.id.toString())
        it?.let {
            topRatedViewModel.selectedGenre.value = it
        }
    }
}