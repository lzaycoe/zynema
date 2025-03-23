package io.lzaycoe.zynema.ui.screens.movies.upcoming
// Trọng Nghĩa
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import io.lzaycoe.zynema.data.model.GenreId
import io.lzaycoe.zynema.data.model.moviedetail.Genre
import io.lzaycoe.zynema.ui.component.Movies

@Composable
fun UpcomingMovie(
    navController: NavController,
    genres: List<Genre>? = null,
) {
    val upComingViewModel = hiltViewModel<UpComingMovieViewModel>()
    Movies(
        navController = navController,
        moviesItems = upComingViewModel.upcomingMovies.collectAsLazyPagingItems(),
        genres = genres,
        selectedName = upComingViewModel.selectedGenre.value
    ) {
        upComingViewModel.filterData.value = GenreId(it?.id.toString())
        it?.let { upComingViewModel.selectedGenre.value = it }
    }
}
