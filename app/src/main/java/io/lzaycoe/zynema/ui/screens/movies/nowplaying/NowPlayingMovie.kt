package io.lzaycoe.zynema.ui.screens.movies.nowplaying

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import io.lzaycoe.zynema.data.model.GenreId
import io.lzaycoe.zynema.data.model.moviedetail.Genre
import io.lzaycoe.zynema.ui.component.Movies

@Composable
fun NowPlayingMovie(
    navController: NavController,
    genres: List<Genre>? = null,
) {
  val nowPlayViewModel = hiltViewModel<NowPlayingMovieViewModel>()
  Movies(
      navController = navController,
      moviesItems = nowPlayViewModel.nowPlayingMovies.collectAsLazyPagingItems(),
      genres = genres,
      selectedName = nowPlayViewModel.selectedGenre.value) {
        nowPlayViewModel.filterData.value = GenreId(it?.id.toString())
        it?.let { nowPlayViewModel.selectedGenre.value = it }
      }
}
