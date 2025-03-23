package io.lzaycoe.zynema.ui.screens.movies.toprated

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import io.lzaycoe.zynema.data.model.GenreId
import io.lzaycoe.zynema.data.model.moviedetail.Genre
import io.lzaycoe.zynema.ui.component.Movies

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
    ) {
        topRatedViewModel.filterData.value = GenreId(it?.id.toString())
        it?.let { topRatedViewModel.selectedGenre.value = it }
    }
}
