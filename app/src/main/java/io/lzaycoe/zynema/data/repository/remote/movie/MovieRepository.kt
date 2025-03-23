package io.lzaycoe.zynema.data.repository.remote.movie

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import io.lzaycoe.zynema.data.datasource.remote.ApiService
import io.lzaycoe.zynema.data.datasource.remote.paging_datasource.GenrePagingDataSource
import io.lzaycoe.zynema.data.datasource.remote.paging_datasource.movie.NowPlayingMoviePagingDataSource
import io.lzaycoe.zynema.data.datasource.remote.paging_datasource.movie.PopularMoviePagingDataSource
import io.lzaycoe.zynema.data.datasource.remote.paging_datasource.movie.TopRatedMoviePagingDataSource
import io.lzaycoe.zynema.data.datasource.remote.paging_datasource.movie.UpcomingMoviePagingDataSource
import io.lzaycoe.zynema.data.model.Genres
import io.lzaycoe.zynema.data.model.MovieItem
import io.lzaycoe.zynema.data.model.SearchBaseModel
import io.lzaycoe.zynema.data.model.artist.Artist
import io.lzaycoe.zynema.data.model.moviedetail.MovieDetail
import io.lzaycoe.zynema.utils.network.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepository
@Inject
constructor(
    private val apiService: ApiService,
) : MovieRepositoryInterface {
    override suspend fun movieDetail(movieId: Int): Flow<DataState<MovieDetail>> = flow {
        emit(DataState.Loading)
        try {
            val searchResult = apiService.movieDetail(movieId)
            emit(DataState.Success(searchResult))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }

    override suspend fun recommendedMovie(movieId: Int): Flow<DataState<List<MovieItem>>> = flow {
        emit(DataState.Loading)
        try {
            val searchResult = apiService.recommendedMovie(movieId)
            emit(DataState.Success(searchResult.results))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }

    override suspend fun movieSearch(searchKey: String): Flow<DataState<SearchBaseModel>> = flow {
        emit(DataState.Loading)
        try {
            val searchResult = apiService.searchMovie(searchKey)
            emit(DataState.Success(searchResult))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }

    override suspend fun genreList(): Flow<DataState<Genres>> = flow {
        emit(DataState.Loading)
        try {
            val genreResult = apiService.genreList()
            emit(DataState.Success(genreResult))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }

    override suspend fun movieCredit(movieId: Int): Flow<DataState<Artist>> = flow {
        emit(DataState.Loading)
        try {
            val artistResult = apiService.movieCredit(movieId)
            emit(DataState.Success(artistResult))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }

    override fun nowPlayingMoviePagingDataSource(genreId: String?): Flow<PagingData<MovieItem>> =
        Pager(
            pagingSourceFactory = { NowPlayingMoviePagingDataSource(apiService, genreId) },
            config = PagingConfig(pageSize = 20)
        )
            .flow

    override fun popularMoviePagingDataSource(genreId: String?): Flow<PagingData<MovieItem>> =
        Pager(
            pagingSourceFactory = { PopularMoviePagingDataSource(apiService, genreId) },
            config = PagingConfig(pageSize = 20)
        )
            .flow

    override fun topRatedMoviePagingDataSource(genreId: String?): Flow<PagingData<MovieItem>> =
        Pager(
            pagingSourceFactory = { TopRatedMoviePagingDataSource(apiService, genreId) },
            config = PagingConfig(pageSize = 20)
        )
            .flow

    override fun upcomingMoviePagingDataSource(genreId: String?): Flow<PagingData<MovieItem>> =
        Pager(
            pagingSourceFactory = { UpcomingMoviePagingDataSource(apiService, genreId) },
            config = PagingConfig(pageSize = 20)
        )
            .flow

    override fun genrePagingDataSource(genreId: String): Flow<PagingData<MovieItem>> =
        Pager(
            pagingSourceFactory = { GenrePagingDataSource(apiService, genreId) },
            config = PagingConfig(pageSize = 20)
        )
            .flow
}
