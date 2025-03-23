package io.lzaycoe.zynema.data.repository.remote.tvseries

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import io.lzaycoe.zynema.data.datasource.remote.ApiService
import io.lzaycoe.zynema.data.datasource.remote.paging_datasource.tv_series.AiringTodayTvSeriesPagingDataSource
import io.lzaycoe.zynema.data.datasource.remote.paging_datasource.tv_series.OnTheAirTvSeriesPagingDataSource
import io.lzaycoe.zynema.data.datasource.remote.paging_datasource.tv_series.PopularTvSeriesPagingDataSource
import io.lzaycoe.zynema.data.datasource.remote.paging_datasource.tv_series.TopRatedTvSeriesPagingDataSource
import io.lzaycoe.zynema.data.model.SearchBaseModel
import io.lzaycoe.zynema.data.model.TvSeriesItem
import io.lzaycoe.zynema.data.model.artist.Artist
import io.lzaycoe.zynema.data.model.tv_series_detail.TvSeriesDetail
import io.lzaycoe.zynema.utils.network.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TvSeriesRepository @Inject constructor(private val apiService: ApiService) :
    TvSeriesRepositoryInterFace {
    override fun airingTodayTvSeriesPagingDataSource(
        genreId: String?
    ): Flow<PagingData<TvSeriesItem>> =
        Pager(
            pagingSourceFactory = { AiringTodayTvSeriesPagingDataSource(apiService, genreId) },
            config = PagingConfig(pageSize = 20)
        )
            .flow

    override fun onTheAirTvSeriesPagingDataSource(genreId: String?): Flow<PagingData<TvSeriesItem>> =
        Pager(
            pagingSourceFactory = { OnTheAirTvSeriesPagingDataSource(apiService, genreId) },
            config = PagingConfig(pageSize = 20)
        )
            .flow

    override fun popularTvSeriesPagingDataSource(genreId: String?): Flow<PagingData<TvSeriesItem>> =
        Pager(
            pagingSourceFactory = { PopularTvSeriesPagingDataSource(apiService, genreId) },
            config = PagingConfig(pageSize = 20)
        )
            .flow

    override fun topRatedTvSeriesPagingDataSource(genreId: String?): Flow<PagingData<TvSeriesItem>> =
        Pager(
            pagingSourceFactory = { TopRatedTvSeriesPagingDataSource(apiService, genreId) },
            config = PagingConfig(pageSize = 20)
        )
            .flow

    override suspend fun searchTvSeries(searchKey: String): Flow<DataState<SearchBaseModel>> =
        flow {
            emit(DataState.Loading)
            try {
                val searchResult = apiService.searchTvSeries(searchKey)
                emit(DataState.Success(searchResult))
            } catch (e: Exception) {
                emit(DataState.Error(e))
            }
        }

    override suspend fun tvSeriesDetail(seriesId: Int): Flow<DataState<TvSeriesDetail>> = flow {
        emit(DataState.Loading)
        try {
            val apiResponse = apiService.tvSeriesDetail(seriesId)
            emit(DataState.Success(apiResponse))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }

    override suspend fun recommendedTvSeries(seriesId: Int): Flow<DataState<List<TvSeriesItem>>> =
        flow {
            emit(DataState.Loading)
            try {
                val apiResponse = apiService.recommendedTvSeries(seriesId)
                emit(DataState.Success(apiResponse.results))
            } catch (e: Exception) {
                emit(DataState.Error(e))
            }
        }

    override suspend fun artistDetail(personId: Int): Flow<DataState<Artist>> = flow {
        emit(DataState.Loading)
        try {
            val apiResponse = apiService.tvSeriesCredit(personId)
            emit(DataState.Success(apiResponse))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }
}
