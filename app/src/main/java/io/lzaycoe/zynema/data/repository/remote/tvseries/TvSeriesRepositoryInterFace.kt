package io.lzaycoe.zynema.data.repository.remote.tvseries

import androidx.paging.PagingData
import io.lzaycoe.zynema.data.model.SearchBaseModel
import io.lzaycoe.zynema.data.model.TvSeriesItem
import io.lzaycoe.zynema.data.model.artist.Artist
import io.lzaycoe.zynema.data.model.tv_series_detail.TvSeriesDetail
import io.lzaycoe.zynema.utils.network.DataState
import kotlinx.coroutines.flow.Flow

interface TvSeriesRepositoryInterFace {
    fun airingTodayTvSeriesPagingDataSource(genreId: String?): Flow<PagingData<TvSeriesItem>>
    fun onTheAirTvSeriesPagingDataSource(genreId: String?): Flow<PagingData<TvSeriesItem>>
    fun popularTvSeriesPagingDataSource(genreId: String?): Flow<PagingData<TvSeriesItem>>
    fun topRatedTvSeriesPagingDataSource(genreId: String?): Flow<PagingData<TvSeriesItem>>
    suspend fun searchTvSeries(searchKey: String): Flow<DataState<SearchBaseModel>>
    suspend fun tvSeriesDetail(seriesId: Int): Flow<DataState<TvSeriesDetail>>
    suspend fun recommendedTvSeries(seriesId: Int): Flow<DataState<List<TvSeriesItem>>>
    suspend fun artistDetail(personId: Int): Flow<DataState<Artist>>
}