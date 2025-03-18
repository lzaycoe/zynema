package io.lzaycoe.zynema.ui.screens.tvseries.top_rated

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import io.lzaycoe.zynema.data.model.GenreId
import io.lzaycoe.zynema.data.model.moviedetail.Genre
import io.lzaycoe.zynema.ui.component.TvSeries

@Composable
fun TopRatedTvSeries(
    navController: NavController,
    genres: List<Genre>? = null,
) {
  val topRatedViewViewModel = hiltViewModel<TopRatedTvSeriesViewModel>()
  TvSeries(
      navController = navController,
      tvSeries = topRatedViewViewModel.topRatedTvSeries.collectAsLazyPagingItems(),
      genres = genres,
      selectedName = topRatedViewViewModel.selectedGenre.value) {
        topRatedViewViewModel.filterData.value = GenreId(it?.id.toString())
        it?.let { topRatedViewViewModel.selectedGenre.value = it }
      }
}
