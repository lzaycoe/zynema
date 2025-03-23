package io.lzaycoe.zynema.ui.screens.celebrities.trending

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import io.lzaycoe.zynema.ui.component.Celebrities

@Composable
fun TrendingCelebrities(
    navController: NavController,
) {
    val trendingCelebritiesViewModel = hiltViewModel<TrendingCelebritiesViewModel>()
    Celebrities(
        navController = navController,
        celebrities = trendingCelebritiesViewModel.trendingCelebrities.collectAsLazyPagingItems()
    )
}
