package io.lzaycoe.zynema.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import io.lzaycoe.zynema.R
import io.lzaycoe.zynema.data.model.moviedetail.Genre
import io.lzaycoe.zynema.ui.screens.artist_detail.ArtistDetail
import io.lzaycoe.zynema.ui.screens.celebrities.popular.PopularCelebrities
import io.lzaycoe.zynema.ui.screens.celebrities.trending.TrendingCelebrities
import io.lzaycoe.zynema.ui.screens.favorite.movie.FavoriteMovie
import io.lzaycoe.zynema.ui.screens.favorite.tvseries.FavoriteTvSeries
import io.lzaycoe.zynema.ui.screens.movies.movie_detail.MovieDetail
import io.lzaycoe.zynema.ui.screens.movies.nowplaying.NowPlayingMovie
import io.lzaycoe.zynema.ui.screens.movies.popular.PopularMovie
import io.lzaycoe.zynema.ui.screens.movies.toprated.TopRatedMovie
import io.lzaycoe.zynema.ui.screens.movies.upcoming.UpcomingMovie
import io.lzaycoe.zynema.ui.screens.tvseries.airing_today.AiringTodayTvSeries
import io.lzaycoe.zynema.ui.screens.tvseries.on_the_air.OnTheAirTvSeries
import io.lzaycoe.zynema.ui.screens.tvseries.popular.PopularTvSeries
import io.lzaycoe.zynema.ui.screens.tvseries.top_rated.TopRatedTvSeries
import io.lzaycoe.zynema.ui.screens.tvseries.tv_series_detail.TvSeriesDetail

@Composable
fun Navigation(
    navController: NavHostController, genres: List<Genre>? = null,
) {
    NavHost(navController, startDestination = Screen.NowPlaying.route) {
        composable(Screen.NowPlaying.route) {
            NowPlayingMovie(
                navController = navController,
                genres
            )
        }
        composable(Screen.Popular.route) {
            PopularMovie(
                navController = navController,
                genres
            )
        }
        composable(Screen.TopRated.route) {
            TopRatedMovie(
                navController = navController,
                genres
            )
        }
        composable(Screen.Upcoming.route) {
            UpcomingMovie(
                navController = navController,
                genres
            )
        }
        composable(
            Screen.MovieDetail.route.plus(Screen.MovieDetail.objectPath),
            arguments = listOf(navArgument(Screen.MovieDetail.objectName) {
                type = NavType.IntType
            })
        ) {
            label = stringResource(R.string.movie_detail)
            val movieId = it.arguments?.getInt(Screen.MovieDetail.objectName)
            movieId?.let {
                MovieDetail(
                    navController = navController, movieId
                )
            }
        }
        composable(
            Screen.ArtistDetail.route.plus(Screen.ArtistDetail.objectPath),
            arguments = listOf(navArgument(Screen.ArtistDetail.objectName) {
                type = NavType.IntType
            })
        ) {
            label = stringResource(R.string.artist_detail)
            val artistId = it.arguments?.getInt(Screen.ArtistDetail.objectName)
            artistId?.let {
                ArtistDetail(
                    navController = navController,
                    artistId
                )
            }
        }
        composable(Screen.AiringTodayTvSeries.route) {
            AiringTodayTvSeries(
                navController = navController,
                genres
            )
        }
        composable(Screen.OnTheAirTvSeries.route) {
            OnTheAirTvSeries(
                navController = navController,
                genres
            )
        }
        composable(Screen.PopularTvSeries.route) {
            PopularTvSeries(
                navController = navController,
                genres
            )
        }
        composable(Screen.TopRatedTvSeries.route) {
            TopRatedTvSeries(
                navController = navController,
                genres
            )
        }
        composable(
            Screen.TvSeriesDetail.route.plus(Screen.TvSeriesDetail.objectPath),
            arguments = listOf(navArgument(Screen.TvSeriesDetail.objectName) {
                type = NavType.IntType
            })
        ) {
            label = stringResource(R.string.tv_series_detail)
            val movieId = it.arguments?.getInt(Screen.TvSeriesDetail.objectName)
            movieId?.let {
                TvSeriesDetail(
                    navController = navController, movieId
                )
            }
        }
        composable(Screen.FavoriteMovie.route) {
            FavoriteMovie(navController)
        }
        composable(Screen.FavoriteTvSeries.route) {
            FavoriteTvSeries(navController)
        }
        composable(Screen.PopularCelebrities.route) {
            PopularCelebrities(navController)
        }
        composable(Screen.TrendingCelebritiesNav.route) {
            TrendingCelebrities(navController)
        }
    }
}

@Composable
fun navigationTitle(navController: NavController): String {
    return when (currentRoute(navController)) {
        Screen.MovieDetail.route -> stringResource(id = R.string.movie_detail)
        Screen.TvSeriesDetail.route -> stringResource(id = R.string.tv_series_detail)
        Screen.ArtistDetail.route -> stringResource(id = R.string.artist_detail)
        Screen.FavoriteMovie.route -> stringResource(id = R.string.favorite_movie)
        Screen.FavoriteTvSeries.route -> stringResource(id = R.string.favorite_tv_series)
        else -> {
            stringResource(R.string.app_name)
        }
    }
}

@Composable
fun currentRoute(navController: NavController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route?.substringBeforeLast("/")
}
