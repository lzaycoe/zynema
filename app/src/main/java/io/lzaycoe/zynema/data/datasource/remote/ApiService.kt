package io.lzaycoe.zynema.data.datasource.remote

import io.lzaycoe.zynema.BuildConfig
import io.lzaycoe.zynema.data.model.BaseModel
import io.lzaycoe.zynema.data.model.Genres
import io.lzaycoe.zynema.data.model.MovieItem
import io.lzaycoe.zynema.data.model.SearchBaseModel
import io.lzaycoe.zynema.data.model.TvSeriesItem
import io.lzaycoe.zynema.data.model.artist.Artist
import io.lzaycoe.zynema.data.model.artist.ArtistDetail
import io.lzaycoe.zynema.data.model.artist.ArtistMovies
import io.lzaycoe.zynema.data.model.celebrities.Celebrity
import io.lzaycoe.zynema.data.model.moviedetail.MovieDetail
import io.lzaycoe.zynema.data.model.tv_series_detail.TvSeriesDetail
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("movie/now_playing")
    suspend fun nowPlayingMovies(
        @Query("page") page: Int,
        @Query("with_genres") genreId: String?
    ): BaseModel<MovieItem>

    @GET("movie/popular")
    suspend fun popularMovies(
        @Query("page") page: Int,
        @Query("with_genres") genreId: String?
    ): BaseModel<MovieItem>

    @GET("movie/top_rated")
    suspend fun topRatedMovies(
        @Query("page") page: Int,
        @Query("with_genres") genreId: String?
    ): BaseModel<MovieItem>

    @GET("movie/upcoming")
    suspend fun upcomingMovies(
        @Query("page") page: Int,
        @Query("with_genres") genreId: String?
    ): BaseModel<MovieItem>

    @GET("movie/{movieId}")
    suspend fun movieDetail(@Path("movieId") movieId: Int): MovieDetail

    @GET("movie/{movieId}/recommendations")
    suspend fun recommendedMovie(@Path("movieId") movieId: Int): BaseModel<MovieItem>

    @GET("search/movie?page=1&include_adult=false")
    suspend fun searchMovie(@Query("query") searchKey: String): SearchBaseModel

    @GET("genre/movie/list")
    suspend fun genreList(@Query("api_key") apiKey: String = BuildConfig.API_KEY): Genres

    @GET("discover/movie")
    suspend fun moviesByGenre(
        @Query("page") page: Int,
        @Query("with_genres") genreId: String,
    ): BaseModel<MovieItem>

    @GET("movie/{movieId}/credits")
    suspend fun movieCredit(@Path("movieId") movieId: Int): Artist

    @GET("person/{personId}")
    suspend fun artistDetail(@Path("personId") personId: Int): ArtistDetail

    @GET("person/{personId}/combined_credits")
    suspend fun artistAllMovies(@Path("personId") movieId: Int): ArtistMovies

    @GET("tv/airing_today")
    suspend fun airingTodayTvSeries(
        @Query("page") page: Int,
        @Query("with_genres") genreId: String?
    ): BaseModel<TvSeriesItem>

    @GET("tv/on_the_air")
    suspend fun onTheAirTvSeries(
        @Query("page") page: Int,
        @Query("with_genres") genreId: String?
    ): BaseModel<TvSeriesItem>

    @GET("tv/popular")
    suspend fun popularTvSeries(
        @Query("page") page: Int,
        @Query("with_genres") genreId: String?
    ): BaseModel<TvSeriesItem>

    @GET("tv/top_rated")
    suspend fun topRatedTvSeries(
        @Query("page") page: Int,
        @Query("with_genres") genreId: String?
    ): BaseModel<TvSeriesItem>

    @GET("tv/{seriesId}")
    suspend fun tvSeriesDetail(@Path("seriesId") seriesId: Int): TvSeriesDetail

    @GET("tv/{seriesId}/recommendations")
    suspend fun recommendedTvSeries(@Path("seriesId") seriesId: Int): BaseModel<TvSeriesItem>

    @GET("tv/{seriesId}/credits")
    suspend fun tvSeriesCredit(@Path("seriesId") seriesId: Int): Artist

    @GET("search/tv?page=1&include_adult=false")
    suspend fun searchTvSeries(@Query("query") searchKey: String): SearchBaseModel

    @GET("person/popular")
    suspend fun popularCelebrities(
        @Query("page") page: Int,
    ): BaseModel<Celebrity>

    @GET("trending/person/week")
    suspend fun trendingCelebrities(
        @Query("page") page: Int,
    ): BaseModel<Celebrity>
}
