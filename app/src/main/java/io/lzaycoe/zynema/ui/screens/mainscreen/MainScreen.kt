package io.lzaycoe.zynema.ui.screens.mainscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import io.lzaycoe.zynema.R
import io.lzaycoe.zynema.data.model.moviedetail.Genre
import io.lzaycoe.zynema.navigation.Navigation
import io.lzaycoe.zynema.navigation.Screen
import io.lzaycoe.zynema.navigation.currentRoute
import io.lzaycoe.zynema.navigation.navigationTitle
import io.lzaycoe.zynema.ui.component.CircularIndeterminateProgressBar
import io.lzaycoe.zynema.ui.component.SearchBar
import io.lzaycoe.zynema.ui.component.SearchUI
import io.lzaycoe.zynema.ui.screens.mainscreen.botton_navigation.BottomNavigationUI
import io.lzaycoe.zynema.ui.screens.mainscreen.tav_view.FavoriteTabView
import io.lzaycoe.zynema.ui.screens.mainscreen.tav_view.TabView
import io.lzaycoe.zynema.ui.theme.FloatingActionBackground
import io.lzaycoe.zynema.ui.theme.cornerRadius
import io.lzaycoe.zynema.utils.ACTIVE_MOVIE_TAB
import io.lzaycoe.zynema.utils.ACTIVE_TV_SERIES_TAB
import io.lzaycoe.zynema.utils.networkconnection.ConnectionState
import io.lzaycoe.zynema.utils.networkconnection.connectivityState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
  val mainViewModel = hiltViewModel<MainViewModel>()
  val navController = rememberNavController()
  val isAppBarVisible = remember { mutableStateOf(true) }
  val connection by connectivityState()
  val isConnected = connection === ConnectionState.Available
  val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
  val isFavoriteActive = remember { mutableStateOf(false) }
  val pagerState = rememberPagerState { 3 }

  // Observe the UI state from the ViewModel
  val uiState by mainViewModel.uiState.collectAsState()

  LaunchedEffect(Unit) { mainViewModel.loadGenres() }

  Scaffold(
      topBar = {
        if (!isAppBarVisible.value) {
          SearchBar(isAppBarVisible, mainViewModel, pagerState.currentPage)
        } else
            CenterAlignedTopAppBar(
                colors =
                    TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                    ),
                title = {
                  Text(
                      text = navigationTitle(navController),
                      maxLines = 1,
                      overflow = TextOverflow.Ellipsis,
                      color = Color.White)
                },
                navigationIcon = {
                  when (currentRoute(navController)) {
                    Screen.MovieDetail.route,
                    Screen.ArtistDetail.route,
                    Screen.TvSeriesDetail.route,
                    Screen.FavoriteMovie.route,
                    Screen.FavoriteTvSeries.route -> {
                      val activeScreen = currentRoute(navController)
                      IconButton(
                          onClick = {
                            if (isFavoriteActive.value &&
                                (activeScreen == Screen.FavoriteMovie.route ||
                                    activeScreen == Screen.FavoriteTvSeries.route)) {
                              val activeMovieTab =
                                  if (pagerState.currentPage == ACTIVE_MOVIE_TAB)
                                      Screen.NowPlaying.route
                                  else Screen.AiringTodayTvSeries.route
                              navController.navigate(activeMovieTab) {
                                popUpTo(navController.graph.startDestinationId) { inclusive = true }
                              }
                              isFavoriteActive.value = false
                            } else {
                              navController.popBackStack()
                            }
                          }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "back arrow",
                                tint = Color.White)
                          }
                    }
                  }
                },
                scrollBehavior = scrollBehavior,
                actions = {
                  IconButton(
                      onClick = {
                        if (!isFavoriteActive.value)
                            navController.navigate(Screen.FavoriteMovie.route)
                        isFavoriteActive.value = true
                      }) {
                        if (currentRoute(navController) !in
                            listOf(
                                Screen.FavoriteMovie.route,
                                Screen.FavoriteTvSeries.route,
                                Screen.MovieDetail.route,
                                Screen.TvSeriesDetail.route,
                                Screen.ArtistDetail.route)) {
                          Icon(
                              imageVector = Icons.Filled.Favorite,
                              contentDescription = "favorite",
                              tint = Color.Gray)
                        }
                      }
                })
      },
      floatingActionButton = {
        if (currentRoute(navController) !in
            listOf(
                Screen.FavoriteMovie.route,
                Screen.FavoriteTvSeries.route,
                Screen.MovieDetail.route,
                Screen.TvSeriesDetail.route,
                Screen.ArtistDetail.route)) {
          FloatingActionButton(
              modifier = Modifier.cornerRadius(30),
              containerColor = FloatingActionBackground,
              onClick = { isAppBarVisible.value = false },
          ) {
            Icon(Icons.Filled.Search, "", tint = Color.White)
          }
        }
      },
      bottomBar = {
        if (currentRoute(navController) in
            listOf(
                Screen.NowPlaying.route,
                Screen.Popular.route,
                Screen.TopRated.route,
                Screen.Upcoming.route,
                Screen.AiringTodayTvSeries.route,
                Screen.OnTheAirTvSeriesNav.route,
                Screen.PopularTvSeries.route,
                Screen.TopRatedTvSeries.route,
                Screen.PopularCelebrities.route,
                Screen.TrendingCelebrities.route,
            )) {
          BottomNavigationUI(navController, pagerState)
        }
      },
      snackbarHost = {
        if (!isConnected) {
          Snackbar(action = {}, modifier = Modifier.padding(8.dp)) {
            Text(text = stringResource(R.string.there_is_no_internet))
          }
        }
      }) { padding ->
        Box(Modifier.padding(padding)) {
          MainView(
              navController = navController,
              pagerState = pagerState,
              genres = uiState.genres?.genres,
              isFavorite = isFavoriteActive.value)

          CircularIndeterminateProgressBar(isDisplayed = uiState.isLoading, 0.1f)

          if (!isAppBarVisible.value) {
            when (pagerState.currentPage) {
              ACTIVE_MOVIE_TAB -> {
                SearchUI(navController, uiState.movieSearchResults, pagerState.currentPage) {
                  isAppBarVisible.value = true
                }
              }
              ACTIVE_TV_SERIES_TAB -> {
                SearchUI(navController, uiState.tvSeriesSearchResults, pagerState.currentPage) {
                  isAppBarVisible.value = true
                }
              }
            }
          }
        }
      }
}

@Composable
fun MainView(
    navController: NavHostController,
    pagerState: PagerState,
    genres: List<Genre>?,
    isFavorite: Boolean,
) {
  Column {
    if (currentRoute(navController) !in
        listOf(Screen.MovieDetail.route, Screen.TvSeriesDetail.route, Screen.ArtistDetail.route)) {
      if (!isFavorite) {
        TabView(navController, pagerState)
      } else {
        FavoriteTabView(navController)
      }
    }
    HorizontalPager(
        state = pagerState, modifier = Modifier.fillMaxSize(), userScrollEnabled = false) {
          Navigation(navController, genres)
        }
  }
}
