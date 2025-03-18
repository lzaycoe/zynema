package io.lzaycoe.zynema.data.repository.local.tvseries

import io.lzaycoe.zynema.data.datasource.local.dao.FavoriteTvSeriesDao
import io.lzaycoe.zynema.data.model.tv_series_detail.TvSeriesDetail
import javax.inject.Inject

class LocalTvSeriesRepository
@Inject
constructor(
    private val tvSeriesDao: FavoriteTvSeriesDao,
) : LocalTvSeriesRepositoryInterface {
  override suspend fun favoriteTvSeries(): List<TvSeriesDetail?> {
    return tvSeriesDao.getAllTvSeriesDetails()
  }

  override suspend fun removeTvSeriesById(tvSeriesId: Int) {
    tvSeriesDao.deleteTvSeriesById(tvSeriesId)
  }
}
