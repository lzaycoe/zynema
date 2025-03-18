package io.lzaycoe.zynema.data.repository.remote.celebrity

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import io.lzaycoe.zynema.data.datasource.remote.ApiService
import io.lzaycoe.zynema.data.datasource.remote.paging_datasource.celebrities.PopularCelebritiesPagingDataSource
import io.lzaycoe.zynema.data.datasource.remote.paging_datasource.celebrities.TrendingCelebritiesPagingDataSource
import io.lzaycoe.zynema.data.model.celebrities.Celebrity
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class CelebrityRepository
@Inject
constructor(
    private val apiService: ApiService,
) : CelebrityRepositoryInterface {
  override fun popularCelebrities(page: Int): Flow<PagingData<Celebrity>> =
      Pager(
              pagingSourceFactory = { PopularCelebritiesPagingDataSource(apiService) },
              config = PagingConfig(pageSize = 20))
          .flow

  override fun trendingCelebrities(page: Int): Flow<PagingData<Celebrity>> =
      Pager(
              pagingSourceFactory = { TrendingCelebritiesPagingDataSource(apiService) },
              config = PagingConfig(pageSize = 20))
          .flow
}
