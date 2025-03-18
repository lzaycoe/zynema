package io.lzaycoe.zynema.data.repository.local.tvseries

import io.lzaycoe.zynema.data.model.tv_series_detail.TvSeriesDetail

interface LocalTvSeriesRepositoryInterface {
  suspend fun favoriteTvSeries(): List<TvSeriesDetail?>

  suspend fun removeTvSeriesById(tvSeriesId: Int)
}
