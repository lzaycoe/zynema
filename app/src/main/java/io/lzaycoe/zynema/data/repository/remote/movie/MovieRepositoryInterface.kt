package io.lzaycoe.zynema.data.repository.remote.movie

import androidx.paging.PagingData
import io.lzaycoe.zynema.data.model.Genres
import io.lzaycoe.zynema.data.model.MovieItem
import io.lzaycoe.zynema.data.model.SearchBaseModel
import io.lzaycoe.zynema.data.model.artist.Artist
import io.lzaycoe.zynema.data.model.moviedetail.MovieDetail
import io.lzaycoe.zynema.utils.network.DataState
import kotlinx.coroutines.flow.Flow

interface MovieRepositoryInterface {
    suspend fun movieDetail(movieId: Int): Flow<DataState<MovieDetail>>

    suspend fun recommendedMovie(movieId: Int): Flow<DataState<List<MovieItem>>>

    suspend fun movieSearch(searchKey: String): Flow<DataState<SearchBaseModel>>

    suspend fun genreList(): Flow<DataState<Genres>>

    suspend fun movieCredit(movieId: Int): Flow<DataState<Artist>>

    fun nowPlayingMoviePagingDataSource(genreId: String?): Flow<PagingData<MovieItem>>

    fun popularMoviePagingDataSource(genreId: String?): Flow<PagingData<MovieItem>>

    fun topRatedMoviePagingDataSource(genreId: String?): Flow<PagingData<MovieItem>>

    fun upcomingMoviePagingDataSource(genreId: String?): Flow<PagingData<MovieItem>>

    fun genrePagingDataSource(genreId: String): Flow<PagingData<MovieItem>>
}
