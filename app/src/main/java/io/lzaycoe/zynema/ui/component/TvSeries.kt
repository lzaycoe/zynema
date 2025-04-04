package io.lzaycoe.zynema.ui.component

import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import io.lzaycoe.zynema.data.model.TvSeriesItem
import io.lzaycoe.zynema.data.model.moviedetail.Genre
import io.lzaycoe.zynema.navigation.Screen
import io.lzaycoe.zynema.navigation.currentRoute
import io.lzaycoe.zynema.ui.theme.DefaultBackgroundColor
import io.lzaycoe.zynema.utils.conditional
import io.lzaycoe.zynema.utils.items
import io.lzaycoe.zynema.utils.pagingLoadingState

@Composable
fun TvSeries(
    navController: NavController,
    tvSeries: LazyPagingItems<TvSeriesItem>,
    genres: List<Genre>? = null,
    selectedName: Genre?,
    onclick: (Genre?) -> Unit,
) {
    val activity = LocalActivity.current
    val progressBar = remember { mutableStateOf(false) }
    val openDialog = remember { mutableStateOf(false) }

    // Handling back press for dialog
    BackHandler(enabled = currentRoute(navController) == Screen.AiringTodayTvSeries.route) {
        openDialog.value = true
    }

    Column(modifier = Modifier.background(DefaultBackgroundColor)) {
        // Display genres if provided
        genres?.let { DisplayGenres(it, selectedName, onclick) }

        // Show loading indicator if progressBar is true
        CircularIndeterminateProgressBar(isDisplayed = progressBar.value, 0.4f)

        // Display TV series items using LazyVerticalGrid
        DisplayTvSeries(tvSeries, navController, genres)

        // Show exit dialog if back button is pressed
        if (openDialog.value) {
            ShowExitDialog(activity, openDialog)
        }
    }

    // Handle loading state for paging
    tvSeries.pagingLoadingState { progressBar.value = it }
}

@Composable
fun DisplayTvSeries(
    tvSeriesItems: LazyPagingItems<TvSeriesItem>,
    navController: NavController,
    genres: List<Genre>?,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier =
            Modifier
                .padding(horizontal = 5.dp)
                .conditional(genres == null) { padding(top = 8.dp) }) {
        items(tvSeriesItems) { item ->
            item?.let {
                ItemView(
                    item = it,
                    navController = navController,
                    itemIdExtractor = { it.id.toString() },
                    itemImageUrlExtractor = { it.posterPath },
                    itemDetailRoute = Screen.TvSeriesDetail.route // Correct route for TvSeriesDetail
                )
            }
        }
    }
}
