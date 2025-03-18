package io.lzaycoe.zynema.ui.screens.movies.popular

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import io.lzaycoe.zynema.data.model.GenreId
import io.lzaycoe.zynema.data.model.moviedetail.Genre
import io.lzaycoe.zynema.ui.component.Movies

@Composable
fun PopularMovie(
    navController: NavController,
    genres: List<Genre>? = null,
) {
  val popularViewModel = hiltViewModel<PopularMovieViewModel>()
  Movies(
      navController = navController,
      moviesItems = popularViewModel.popularMovies.collectAsLazyPagingItems(),
      genres = genres,
      selectedName = popularViewModel.selectedGenre.value) {
        popularViewModel.filterData.value = GenreId(it?.id.toString())
        it?.let { popularViewModel.selectedGenre.value = it }
      }
}
