package io.lzaycoe.zynema.ui.screens.tvseries.airing_today

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import io.lzaycoe.zynema.data.model.GenreId
import io.lzaycoe.zynema.data.model.moviedetail.Genre
import io.lzaycoe.zynema.ui.component.TvSeries

@Composable
fun AiringTodayTvSeries(
    navController: NavController,
    genres: List<Genre>? = null,
) {
    val airingTodayViewModel = hiltViewModel<AiringTodayTvSeriesViewModel>()
    TvSeries(
        navController = navController,
        tvSeries = airingTodayViewModel.airingTodayTvSeries.collectAsLazyPagingItems(),
        genres = genres,
        selectedName = airingTodayViewModel.selectedGenre.value
    ) {
        airingTodayViewModel.filterData.value = GenreId(it?.id.toString())
        it?.let { airingTodayViewModel.selectedGenre.value = it }
    }
}
