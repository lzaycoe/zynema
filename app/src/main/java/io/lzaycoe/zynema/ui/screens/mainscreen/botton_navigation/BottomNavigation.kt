package io.lzaycoe.zynema.ui.screens.mainscreen.botton_navigation

import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import io.lzaycoe.zynema.navigation.Screen
import io.lzaycoe.zynema.navigation.currentRoute
import io.lzaycoe.zynema.utils.singleTopNavigator

@Composable
fun BottomNavigationUI(navController: NavController, pagerState: PagerState) {
    NavigationBar {
        val items =
            when (pagerState.currentPage) {
                0 -> {
                    listOf(
                        Screen.NowPlayingNav,
                        Screen.PopularNav,
                        Screen.TopRatedNav,
                        Screen.UpcomingNav,
                    )
                }

                1 -> {
                    listOf(
                        Screen.AiringTodayTvSeriesNav,
                        Screen.OnTheAirTvSeriesNav,
                        Screen.PopularTvSeriesNav,
                        Screen.TopRatedTvSeriesNav,
                    )
                }

                else -> {
                    listOf(
                        Screen.PopularCelebritiesNav,
                        Screen.TrendingCelebritiesNav,
                    )
                }
            }

        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = item.navIcon,
                label = { Text(text = stringResource(id = item.title)) },
                selected = currentRoute(navController) == item.route,
                onClick = { navController.singleTopNavigator(item.route) })
        }
    }
}
