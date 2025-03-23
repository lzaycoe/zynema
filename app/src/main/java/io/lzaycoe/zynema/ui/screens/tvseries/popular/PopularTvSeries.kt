package io.lzaycoe.zynema.ui.screens.tvseries.popular
// Quốc Chương
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import io.lzaycoe.zynema.data.model.GenreId
import io.lzaycoe.zynema.data.model.moviedetail.Genre
import io.lzaycoe.zynema.ui.component.TvSeries

@Composable
fun PopularTvSeries(
    navController: NavController,
    genres: List<Genre>? = null,
) {
    val popularViewViewModel = hiltViewModel<PopularTvSeriesViewModel>()
    TvSeries(
        navController = navController,
        tvSeries = popularViewViewModel.popularTvSeries.collectAsLazyPagingItems(),
        genres = genres,
        selectedName = popularViewViewModel.selectedGenre.value
    ) {
        popularViewViewModel.filterData.value = GenreId(it?.id.toString())
        it?.let { popularViewViewModel.selectedGenre.value = it }
    }
}
