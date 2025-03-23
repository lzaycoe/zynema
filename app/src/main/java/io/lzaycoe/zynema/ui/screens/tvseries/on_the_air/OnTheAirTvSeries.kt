package io.lzaycoe.zynema.ui.screens.tvseries.on_the_air

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import io.lzaycoe.zynema.data.model.GenreId
import io.lzaycoe.zynema.data.model.moviedetail.Genre
import io.lzaycoe.zynema.ui.component.TvSeries

@Composable
fun OnTheAirTvSeries(
    navController: NavController,
    genres: List<Genre>? = null,
) {
    val onTheAirViewViewModel = hiltViewModel<OnTheAirTvSeriesViewModel>()
    TvSeries(
        navController = navController,
        tvSeries = onTheAirViewViewModel.onTheAirTvSeries.collectAsLazyPagingItems(),
        genres = genres,
        selectedName = onTheAirViewViewModel.selectedGenre.value
    ) {
        onTheAirViewViewModel.filterData.value = GenreId(it?.id.toString())
        it?.let { onTheAirViewViewModel.selectedGenre.value = it }
    }
}
